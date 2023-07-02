package com.retheviper.choseikun.plugins

import com.retheviper.choseikun.application.model.request.CreateEventRequest
import com.retheviper.choseikun.application.model.request.CreateParticipantRequest
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.requestvalidation.RequestValidation
import io.ktor.server.plugins.requestvalidation.ValidationResult

fun Application.configureRequestValidation() {
    install(RequestValidation) {
        validate<CreateEventRequest> { event ->
            when {
                event.name.isBlank() -> ValidationResult.Invalid("Event name is empty.")
                event.eventCandidates.isEmpty() -> ValidationResult.Invalid("Event candidates is empty.")
                else -> ValidationResult.Valid
            }
        }

        validate<CreateParticipantRequest> { participant ->
            when {
                participant.name.isBlank() -> ValidationResult.Invalid("Participant name is empty.")
                participant.eventCandidateAvailabilities.isEmpty() -> ValidationResult.Invalid("Participant event candidate availabilities is empty.")
                else -> ValidationResult.Valid
            }
        }
    }
}