package com.retheviper.choseikun.application.route

import com.retheviper.choseikun.application.model.request.CreateParticipantRequest
import com.retheviper.choseikun.application.model.request.UpdateParticipantRequest
import com.retheviper.choseikun.domain.model.EventId
import com.retheviper.choseikun.domain.model.Participant
import com.retheviper.choseikun.domain.model.ParticipantId
import com.retheviper.choseikun.domain.service.ParticipantService
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.ktor.ext.inject
import java.util.UUID

fun Route.routeParticipant() {
    val service by inject<ParticipantService>()

    route("/event/{eventId}") {
        route("/participant") {
            post {
                val eventId = EventId(UUID.fromString(call.parameters["id"]))
                val request = call.receive<CreateParticipantRequest>()
                transaction { service.create(eventId, Participant.from(request)) }
                call.respond(HttpStatusCode.Created)
            }

            put("/{id}") {
                val id = ParticipantId(UUID.fromString(call.parameters["id"]))
                val request = call.receive<UpdateParticipantRequest>()
                transaction { service.update(Participant.from(id, request)) }
                call.respond(HttpStatusCode.Accepted)
            }
        }
    }
}