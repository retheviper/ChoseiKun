package com.retheviper.choseikun.domain.model

import com.retheviper.choseikun.web.model.request.CreateParticipantRequest
import com.retheviper.choseikun.web.model.request.UpdateParticipantRequest
import java.time.LocalDateTime
import java.util.UUID

data class Participant(
    val id: UUID,
    val name: String,
    val availabilities: Map<LocalDateTime, Availability>
) {
    companion object {
        fun from(request: CreateParticipantRequest): Participant {
            return Participant(
                id = UUID.randomUUID(),
                name = request.name,
                availabilities = request.eventCandidateAvailabilities.associate { it.eventCandidate to it.availability }.toMap()
            )
        }

        fun from(request: UpdateParticipantRequest): Participant {
            return Participant(
                id = UUID.randomUUID(),
                name = request.name,
                availabilities = request.eventCandidateAvailabilities.associate { it.eventCandidate to it.availability }.toMap()
            )
        }
    }
}
