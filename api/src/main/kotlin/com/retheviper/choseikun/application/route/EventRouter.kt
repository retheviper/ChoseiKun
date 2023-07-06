package com.retheviper.choseikun.application.route

import com.retheviper.choseikun.application.model.request.CreateEventRequest
import com.retheviper.choseikun.application.model.request.UpdateEventRequest
import com.retheviper.choseikun.application.model.response.EventResponse
import com.retheviper.choseikun.domain.model.Event
import com.retheviper.choseikun.domain.model.EventId
import com.retheviper.choseikun.domain.service.EventService
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.ktor.ext.inject
import java.util.UUID

fun Route.routeEvent() {
    val service by inject<EventService>()

    route("/event") {
        post {
            val request = call.receive<CreateEventRequest>()
            val dto = transaction { service.create(Event.from(request)) }
            call.respond(EventResponse.from(dto))
        }

        route("/{id}") {
            get {
                val id = EventId(UUID.fromString(call.parameters["id"]))
                val dto = transaction { service.find(id) }
                call.respond(EventResponse.from(dto))
            }

            put {
                val id = EventId(UUID.fromString(call.parameters["id"]))
                val request = call.receive<UpdateEventRequest>()
                val dto = transaction { service.update(Event.from(id, request)) }
                call.respond(EventResponse.from(dto))
            }
        }
    }
}