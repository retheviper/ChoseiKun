package com.retheviper.choseikun.domain.model

import com.retheviper.choseikun.application.model.request.CreateEventRequest
import com.retheviper.choseikun.application.model.request.UpdateEventRequest
import java.util.UUID

data class Event(
    val id: UUID,
    val name: String,
    val description: String,
    val candidates: List<EventCandidate>
) {
    companion object {
        fun from(request: CreateEventRequest): Event {
            return Event(
                id = UUID.randomUUID(),
                name = request.name.trim(),
                description = request.description.trim(),
                candidates = request.eventCandidates.map { EventCandidate.from(it) }
            )
        }

        fun from(id: UUID, request: UpdateEventRequest): Event {
            return Event(
                id = id,
                name = request.name.trim(),
                description = request.description.trim(),
                candidates = request.eventCandidates.map { EventCandidate.from(it) }
            )
        }
    }
}
