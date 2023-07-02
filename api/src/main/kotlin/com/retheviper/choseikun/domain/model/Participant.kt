package com.retheviper.choseikun.domain.model

import com.retheviper.choseikun.application.model.request.CreateParticipantRequest
import com.retheviper.choseikun.application.model.request.UpdateParticipantRequest
import com.retheviper.choseikun.infrastructure.table.ParticipantTable.comment
import java.time.LocalDateTime
import java.util.UUID

data class Participant(
    val id: ParticipantId,
    val name: String,
    val comment: String,
    val availabilities: Map<EventCandidateId, Availability>
) {
    companion object {
        fun from(request: CreateParticipantRequest): Participant {
            return Participant(
                id = ParticipantId(UUID.randomUUID()),
                name = request.name.trim(),
                comment = request.comment.trim(),
                availabilities = request.eventCandidateAvailabilities.associate { EventCandidateId(it.eventCandidateId) to it.availability }.toMap()
            )
        }

        fun from(id: ParticipantId, request: UpdateParticipantRequest): Participant {
            return Participant(
                id = id,
                name = request.name.trim(),
                comment = request.comment.trim(),
                availabilities = request.eventCandidateAvailabilities.associate { EventCandidateId(it.eventCandidateId) to it.availability }.toMap()
            )
        }
    }
}
