package com.retheviper.choseikun.domain.service

import com.retheviper.choseikun.domain.model.Availability
import com.retheviper.choseikun.domain.model.Event
import com.retheviper.choseikun.domain.model.EventId
import com.retheviper.choseikun.domain.model.EventParticipants
import com.retheviper.choseikun.infrastructure.repository.EventRepository
import com.retheviper.choseikun.infrastructure.repository.ParticipantRepository
import io.ktor.server.plugins.NotFoundException

class EventService(
    private val eventRepository: EventRepository,
    private val participantRepository: ParticipantRepository
) {

    fun create(dto: Event): Event {
        eventRepository.create(dto)
        eventRepository.createCandidates(dto.id, dto.candidates)
        return dto
    }

    fun find(id: EventId): EventParticipants {
        val event = eventRepository.find(id) ?: throw NotFoundException("Event not found with id: $id")
        val participants = participantRepository.findBy(id)
        return EventParticipants(event, participants)
    }

    fun update(dto: Event): EventParticipants {
        val event = eventRepository.find(dto.id) ?: throw NotFoundException("Event not found with id: ${dto.id}")

        eventRepository.update(dto)

        val currentCandidates = event.candidates.toSet()
        val newCandidates = dto.candidates.toSet()

        (currentCandidates - newCandidates).let {
            eventRepository.deleteCandidates(it.toList())
        }

        (newCandidates - currentCandidates).let {
            val candidateIds = eventRepository.createCandidates(dto.id, it.toList())
            val participants = participantRepository.findBy(dto.id)
            participants.forEach { participant ->
                participantRepository.create(dto.id, participant.copy(availabilities = participant.availabilities + candidateIds.associateWith { Availability.UNKNOWN }))
            }
        }

        (currentCandidates intersect newCandidates).let {
            eventRepository.updateCandidates(it.toList())
        }

        return find(dto.id)
    }
}