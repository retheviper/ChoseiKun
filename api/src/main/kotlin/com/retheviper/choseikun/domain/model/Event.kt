package com.retheviper.choseikun.domain.model

import com.retheviper.choseikun.web.model.request.CreateEventRequest
import com.retheviper.choseikun.web.model.request.UpdateEventRequest
import java.util.UUID

data class Event(
    val id: UUID,
    val name: String,
    val candidates: List<EventCandidate>
) {
    companion object {
        fun from(request: CreateEventRequest): Event {
            return Event(
                id = UUID.randomUUID(),
                name = request.name,
                candidates = request.eventCandidates.map { EventCandidate.from(it) }
            )
        }

        fun from(request: UpdateEventRequest): Event {
            return Event(
                id = UUID.randomUUID(),
                name = request.name,
                candidates = request.eventCandidates.map { EventCandidate.from(it) }
            )
        }
    }
}
