package com.retheviper

import com.retheviper.plugins.configureDatabases
import com.retheviper.plugins.configureHTTP
import com.retheviper.plugins.configureRouting
import com.retheviper.plugins.configureSerialization
import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureHTTP()
    configureSerialization()
    configureDatabases()
    configureRouting()
}
