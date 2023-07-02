package com.retheviper.choseikun.domain.model

data class EventParticipants(
    val event: Event,
    val participants: List<Participant>
)