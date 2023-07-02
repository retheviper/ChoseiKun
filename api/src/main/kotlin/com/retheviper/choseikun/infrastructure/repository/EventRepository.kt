package com.retheviper.choseikun.infrastructure.repository

import com.retheviper.choseikun.domain.model.Event
import com.retheviper.choseikun.domain.model.EventCandidate
import com.retheviper.choseikun.domain.model.EventCandidateId
import com.retheviper.choseikun.domain.model.EventId
import com.retheviper.choseikun.infrastructure.table.EventCandidateTable
import com.retheviper.choseikun.infrastructure.table.EventTable
import org.jetbrains.exposed.sql.SqlExpressionBuilder.inList
import org.jetbrains.exposed.sql.batchInsert
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.update
import java.time.LocalDateTime

class EventRepository {

    fun find(id: EventId): Event? {
        val event = EventTable.select { EventTable.id eq id.value }.singleOrNull() ?: return null

        val candidates = EventCandidateTable.select { EventCandidateTable.eventId eq id.value }.map {
            EventCandidate(
                id = EventCandidateId(it[EventCandidateTable.id].value),
                datetime = it[EventCandidateTable.datetime]
            )
        }

        return Event(
            id = EventId(event[EventTable.id].value),
            name = event[EventTable.name],
            description = event[EventTable.description],
            candidates = candidates
        )
    }

    fun create(event: Event) {
        EventTable.insert {
            it[id] = event.id.value
            it[name] = event.name
            it[description] = event.description
            it[created] = LocalDateTime.now()
            it[updated] = LocalDateTime.now()
        }
    }

    fun createCandidates(eventId: EventId, candidates: List<EventCandidate>): List<EventCandidateId> {
        return EventCandidateTable.batchInsert(candidates) {
            this[EventCandidateTable.id] = it.id.value
            this[EventCandidateTable.eventId] = eventId.value
            this[EventCandidateTable.datetime] = it.datetime
            this[EventCandidateTable.created] = LocalDateTime.now()
            this[EventCandidateTable.updated] = LocalDateTime.now()
        }.map {
            EventCandidateId(it[EventCandidateTable.id].value)
        }
    }

    fun update(event: Event) {
        EventTable.update({ EventTable.id eq event.id.value }) {
            it[name] = event.name
            it[description] = event.description
            it[updated] = LocalDateTime.now()
        }
    }

    fun updateCandidates(candidates: List<EventCandidate>) {
        candidates.forEach { candidate ->
            EventCandidateTable.update({ EventCandidateTable.id eq candidate.id.value }) {
                it[datetime] = candidate.datetime
                it[updated] = LocalDateTime.now()
            }
        }
    }

    fun deleteCandidates(candidates: List<EventCandidate>) {
        EventCandidateTable.deleteWhere { EventCandidateTable.id inList candidates.map { it.id.value } }
    }
}