package com.retheviper.choseikun.plugins

import com.retheviper.choseikun.application.route.routeEvent
import com.retheviper.choseikun.application.route.routeParticipant
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import io.ktor.server.routing.routing

fun Application.configureRouting() {
    routing {
        route("/v1") {
            get {
                call.respondText("Hello World!")
            }
            routeEvent()
            routeParticipant()
        }
    }
}
