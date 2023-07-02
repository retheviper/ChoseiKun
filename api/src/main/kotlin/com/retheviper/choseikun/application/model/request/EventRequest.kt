package com.retheviper.choseikun.application.model.request

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.util.UUID

@Serializable
data class CreateEventRequest(
    val name: String,
    val description: String,
    val eventCandidates: List<@Contextual LocalDateTime>,
)

@Serializable
data class UpdateEventRequest(
    val name: String,
    val description: String,
    val eventCandidates: List<@Contextual LocalDateTime>,
)