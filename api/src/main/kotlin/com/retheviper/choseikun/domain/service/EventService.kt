package com.retheviper.choseikun.domain.service

import com.retheviper.choseikun.domain.model.Event
import com.retheviper.choseikun.infrastructure.repository.EventRepository
import com.retheviper.choseikun.infrastructure.repository.ParticipantRepository
import java.util.UUID

class EventService(private val eventRepository: EventRepository) {

    fun createEvent(dto: Event) {
    }

    fun getEvent(id: UUID) {
    }

    fun updateEvent(dto: Event) {
    }
}