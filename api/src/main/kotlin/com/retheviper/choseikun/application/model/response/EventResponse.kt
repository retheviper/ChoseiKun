package com.retheviper.choseikun.application.model.response

import com.retheviper.choseikun.domain.model.Availability
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.util.UUID

@Serializable
data class EventResponse(
    @Contextual
    val id: UUID,
    val name: String,
    val candidates: List<EventCandidateResponse>
)

@Serializable
data class EventCandidateResponse(
    @Contextual
    val id: UUID,
    @Contextual
    val datetime: LocalDateTime,
    val participants: List<ParticipantResponse>
)

@Serializable
data class ParticipantResponse(
    @Contextual
    val id: UUID,
    val name: String,
    val availability: Availability
)