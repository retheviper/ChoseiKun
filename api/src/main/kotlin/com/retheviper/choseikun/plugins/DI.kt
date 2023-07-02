package com.retheviper.choseikun.plugins

import com.retheviper.choseikun.domain.service.EventService
import com.retheviper.choseikun.domain.service.ParticipantService
import io.ktor.server.application.*
import io.ktor.server.application.Application
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureDI() {
    val domainModule = module {
        single { EventService() }
        single { ParticipantService() }
    }

    install(Koin) {
        slf4jLogger()
        modules(
            listOf(
                domainModule,
//                infrastructureModule,
//                applicationModule
            )
        )
    }
}