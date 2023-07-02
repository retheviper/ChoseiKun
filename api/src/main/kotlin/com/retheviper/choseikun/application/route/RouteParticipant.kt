package com.retheviper.choseikun.application.route

import com.retheviper.choseikun.domain.service.ParticipantService
import com.retheviper.choseikun.application.model.request.CreateParticipantRequest
import com.retheviper.choseikun.application.model.request.UpdateParticipantRequest
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject
import java.util.UUID

fun Route.routeParticipant() {
    val service by inject<ParticipantService>()

    route("/participant") {
        post {
            val request = call.receive<CreateParticipantRequest>()
        }

        put("/{id}") {
            val id = call.parameters["id"]?.let { UUID.fromString(it) }
            val request = call.receive<UpdateParticipantRequest>()
        }
    }
}