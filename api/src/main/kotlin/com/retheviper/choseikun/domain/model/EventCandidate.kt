package com.retheviper.choseikun.domain.model

import java.time.LocalDateTime
import java.util.UUID

data class EventCandidate(
    val id: EventCandidateId,
    val datetime: LocalDateTime
) {
    companion object {
        fun from(datetime: LocalDateTime): EventCandidate {
            return EventCandidate(
                id = EventCandidateId(UUID.randomUUID()),
                datetime = datetime
            )
        }
    }
}