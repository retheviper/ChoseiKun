package com.retheviper.choseikun.domain.model

import java.time.LocalDateTime
import java.util.UUID

data class EventCandidate(
    val id: UUID,
    val datetime: LocalDateTime
) {
    companion object {
        fun from(datetime: LocalDateTime): EventCandidate {
            return EventCandidate(
                id = UUID.randomUUID(),
                datetime = datetime
            )
        }
    }
}