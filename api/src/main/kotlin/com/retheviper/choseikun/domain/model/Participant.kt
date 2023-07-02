package com.retheviper.choseikun.domain.model

import com.retheviper.choseikun.application.model.request.CreateParticipantRequest
import com.retheviper.choseikun.application.model.request.UpdateParticipantRequest
import java.time.LocalDateTime
import java.util.UUID

data class Participant(
    val id: UUID,
    val name: String,
    val comment: String,
    val availabilities: Map<UUID, Availability>
) {
    companion object {
        fun from(request: CreateParticipantRequest): Participant {
            return Participant(
                id = UUID.randomUUID(),
                name = request.name.trim(),
                comment = request.comment.trim(),
                availabilities = request.eventCandidateAvailabilities.associate { it.eventCandidateId to it.availability }.toMap()
            )
        }

        fun from(id: UUID, request: UpdateParticipantRequest): Participant {
            return Participant(
                id = id,
                name = request.name.trim(),
                comment = request.comment.trim(),
                availabilities = request.eventCandidateAvailabilities.associate { it.eventCandidateId to it.availability }.toMap()
            )
        }
    }
}
