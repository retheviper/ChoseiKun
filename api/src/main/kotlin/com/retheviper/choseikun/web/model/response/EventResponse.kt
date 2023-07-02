package com.retheviper.choseikun.web.model.response

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
    val datetime: LocalDateTime,
    val participants: List<ParticipantResponse>
)

@Serializable
data class ParticipantResponse(
    val name: String,
    val availability: Availability
)