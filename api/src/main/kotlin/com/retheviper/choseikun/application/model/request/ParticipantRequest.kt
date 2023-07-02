package com.retheviper.choseikun.application.model.request

import com.retheviper.choseikun.domain.model.Availability
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.util.UUID

@Serializable
data class CreateParticipantRequest(
    val name: String,
    val comment: String,
    val eventCandidateAvailabilities: List<EventCandidateAvailability>
)

@Serializable
data class UpdateParticipantRequest(
    val name: String,
    val comment: String,
    val eventCandidateAvailabilities: List<EventCandidateAvailability>
)

@Serializable
data class EventCandidateAvailability(
    @Contextual
    val eventCandidateId: UUID,
    val availability: Availability
)