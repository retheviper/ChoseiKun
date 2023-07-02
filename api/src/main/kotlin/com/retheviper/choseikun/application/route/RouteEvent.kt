package com.retheviper.choseikun.application.route

import com.retheviper.choseikun.domain.service.EventService
import com.retheviper.choseikun.application.model.request.CreateEventRequest
import com.retheviper.choseikun.application.model.request.UpdateEventRequest
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject
import java.util.UUID

fun Route.routeEvent() {
    val service by inject<EventService>()

    route("/event") {
        get("/{id}") {
            val id = call.parameters["id"].let { UUID.fromString(it) }
        }

        post {
            val request = call.receive<CreateEventRequest>()
        }

        put("/{id}") {
            val id = call.parameters["id"].let { UUID.fromString(it) }
            val request = call.receive<UpdateEventRequest>()
        }
    }
}