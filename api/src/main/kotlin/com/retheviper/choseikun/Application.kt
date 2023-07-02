package com.retheviper.choseikun

import com.retheviper.choseikun.plugins.configureDI
import com.retheviper.choseikun.plugins.configureDatabases
import com.retheviper.choseikun.plugins.configureHTTP
import com.retheviper.choseikun.plugins.configureRouting
import com.retheviper.choseikun.plugins.configureSerialization
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
    configureDI()
    configureRouting()
}
