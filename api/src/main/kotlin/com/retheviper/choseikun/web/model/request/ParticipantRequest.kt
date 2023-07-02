package com.retheviper.choseikun.web.model.request

import com.retheviper.choseikun.domain.model.Availability
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class CreateParticipantRequest(
    val name: String,
    val eventCandidateAvailabilities: List<EventCandidateAvailability>
)

@Serializable
data class UpdateParticipantRequest(
    val name: String,
    val eventCandidateAvailabilities: List<EventCandidateAvailability>
)

@Serializable
data class EventCandidateAvailability(
    @Contextual
    val eventCandidate: LocalDateTime,
    val availability: Availability
)