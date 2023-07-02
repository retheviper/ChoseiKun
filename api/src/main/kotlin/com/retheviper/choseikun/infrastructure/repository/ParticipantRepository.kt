package com.retheviper.choseikun.infrastructure.repository

import com.retheviper.choseikun.domain.model.EventCandidateId
import com.retheviper.choseikun.domain.model.EventId
import com.retheviper.choseikun.domain.model.Participant
import com.retheviper.choseikun.domain.model.ParticipantId
import com.retheviper.choseikun.infrastructure.table.ParticipantAvailabilityTable
import com.retheviper.choseikun.infrastructure.table.ParticipantAvailabilityTable.participantId
import com.retheviper.choseikun.infrastructure.table.ParticipantTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.update
import java.time.LocalDateTime

class ParticipantRepository {

    fun findBy(eventId: EventId): List<Participant> {
        val availabilities = ParticipantAvailabilityTable
            .leftJoin(ParticipantTable)
            .select { ParticipantTable.eventId eq eventId.value }
            .groupBy { ParticipantId(it[participantId].value) }
            .mapValues { (_, values) ->
                values.associate {
                    val eventCandidateId = EventCandidateId(it[ParticipantAvailabilityTable.eventCandidateId].value)
                    val availability = it[ParticipantAvailabilityTable.availability]
                    eventCandidateId to availability
                }
            }.toMap()

        return ParticipantTable.select { ParticipantTable.eventId eq eventId.value }
            .map {
                val participantId = ParticipantId(it[ParticipantTable.id].value)
                Participant(
                    id = participantId,
                    name = it[ParticipantTable.name],
                    comment = it[ParticipantTable.comment],
                    availabilities = availabilities[participantId] ?: emptyMap()
                )
            }
    }

    fun find(id: ParticipantId): Participant? {
        val participant = ParticipantTable.select { ParticipantTable.id eq id.value }
            .singleOrNull() ?: return null

        val availabilities = ParticipantAvailabilityTable.select { participantId eq id.value }
            .associate {
                val eventCandidateId = EventCandidateId(it[ParticipantAvailabilityTable.eventCandidateId].value)
                val availability = it[ParticipantAvailabilityTable.availability]
                eventCandidateId to availability
            }

        return Participant(
            id = ParticipantId(participant[ParticipantTable.id].value),
            name = participant[ParticipantTable.name],
            comment = participant[ParticipantTable.comment],
            availabilities = availabilities
        )
    }

    fun create(eventId: EventId, participant: Participant) {
        ParticipantTable.insert {
            it[id] = participant.id.value
            it[name] = participant.name
            it[comment] = participant.comment
            it[this.eventId] = eventId.value
            it[created] = LocalDateTime.now()
            it[updated] = LocalDateTime.now()
        }

        participant.availabilities.forEach { (eventCandidateId, availability) ->
            ParticipantAvailabilityTable.insert {
                it[this.eventCandidateId] = eventCandidateId.value
                it[this.participantId] = participant.id.value
                it[this.availability] = availability
                it[this.created] = LocalDateTime.now()
                it[this.updated] = LocalDateTime.now()
            }
        }
    }

    fun update(participant: Participant) {
        ParticipantTable.update({ ParticipantTable.id eq participant.id.value }) {
            it[name] = participant.name
            it[comment] = participant.comment
            it[updated] = LocalDateTime.now()
        }

        participant.availabilities.forEach { (eventCandidateId, availability) ->
            ParticipantAvailabilityTable.update({ ParticipantAvailabilityTable.eventCandidateId eq eventCandidateId.value }) {
                it[this.availability] = availability
                it[updated] = LocalDateTime.now()
            }
        }
    }
}