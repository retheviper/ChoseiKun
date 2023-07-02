package com.retheviper.choseikun.domain.model

import com.retheviper.choseikun.application.model.request.CreateEventRequest
import com.retheviper.choseikun.application.model.request.UpdateEventRequest
import java.util.UUID

data class Event(
    val id: EventId,
    val name: String,
    val description: String,
    val candidates: List<EventCandidate>
) {
    companion object {
        fun from(request: CreateEventRequest): Event {
            return Event(
                id = EventId(UUID.randomUUID()),
                name = request.name.trim(),
                description = request.description.trim(),
                candidates = request.eventCandidates.map { EventCandidate.from(it) }
            )
        }

        fun from(id: EventId, request: UpdateEventRequest): Event {
            return Event(
                id = id,
                name = request.name.trim(),
                description = request.description.trim(),
                candidates = request.eventCandidates.map { EventCandidate.from(it) }
            )
        }
    }
}
