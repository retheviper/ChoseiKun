package com.retheviper.choseikun.application.model.response

import com.retheviper.choseikun.domain.model.Availability
import com.retheviper.choseikun.domain.model.Event
import com.retheviper.choseikun.domain.model.EventCandidate
import com.retheviper.choseikun.domain.model.EventCandidateId
import com.retheviper.choseikun.domain.model.EventParticipants
import com.retheviper.choseikun.domain.model.Participant
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.util.UUID

@Serializable
data class EventResponse(
    @Contextual
    val id: UUID,
    val name: String,
    val candidates: List<EventCandidateResponse>,
    val participants: List<ParticipantResponse>
) {
    companion object {
        fun from(dto: EventParticipants): EventResponse {
            return EventResponse(
                id = dto.event.id.value,
                name = dto.event.name,
                candidates = dto.event.candidates.map { EventCandidateResponse.from(it) },
                participants = dto.participants.map { ParticipantResponse.from(it) }
            )
        }
    }
}

@Serializable
data class EventCandidateResponse(
    @Contextual
    val id: UUID,
    @Contextual
    val datetime: LocalDateTime
) {
    companion object {
        fun from(dto: EventCandidate): EventCandidateResponse {
            return EventCandidateResponse(
                id = dto.id.value,
                datetime = dto.datetime
            )
        }
    }
}

@Serializable
data class ParticipantResponse(
    @Contextual
    val id: UUID,
    val name: String,
    val availabilities: Map<@Contextual UUID, Availability>
) {
    companion object {
        fun from(dto: Participant): ParticipantResponse {
            return ParticipantResponse(
                id = dto.id.value,
                name = dto.name,
                availabilities = dto.availabilities.mapKeys { it.key.value }
            )
        }
    }
}