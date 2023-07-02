package com.retheviper.choseikun.domain.service

import com.retheviper.choseikun.domain.model.EventId
import com.retheviper.choseikun.domain.model.Participant
import com.retheviper.choseikun.infrastructure.repository.EventRepository
import com.retheviper.choseikun.infrastructure.repository.ParticipantRepository
import io.ktor.server.plugins.NotFoundException

class ParticipantService(
    private val eventRepository: EventRepository,
    private val participantRepository: ParticipantRepository
) {

    fun create(eventId: EventId, dto: Participant): Participant {
        val currentEvent = eventRepository.find(eventId) ?: throw NotFoundException("Event not found with id: $eventId")

        if (currentEvent.candidates.size != dto.availabilities.size) {
            throw IllegalArgumentException("Availability size is not match with current event with id: $eventId")
        }

        participantRepository.create(eventId, dto)
        return dto
    }

    fun update(dto: Participant): Participant {
        val currentParticipant =
            participantRepository.find(dto.id) ?: throw NotFoundException("Participant not found with id: ${dto.id}")

        if (currentParticipant.availabilities.size != dto.availabilities.size) {
            throw IllegalArgumentException("Availability size is not match with current participant with id: ${dto.id}")
        }

        participantRepository.update(dto)
        return dto
    }
}