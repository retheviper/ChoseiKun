package com.retheviper.choseikun.web.model.request

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class CreateEventRequest(
    val name: String,
    val eventCandidates: List<@Contextual LocalDateTime>,
)

@Serializable
data class UpdateEventRequest(
    val name: String,
    val eventCandidates: List<@Contextual LocalDateTime>,
)