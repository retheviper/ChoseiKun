package com.retheviper.choseikun.domain.model

import java.util.UUID

@JvmInline
value class EventId(val value: UUID) {
    override fun toString(): String {
        return value.toString()
    }
}

@JvmInline
value class EventCandidateId(val value: UUID) {
    override fun toString(): String {
        return value.toString()
    }
}

@JvmInline
value class ParticipantId(val value: UUID) {
    override fun toString(): String {
        return value.toString()
    }
}