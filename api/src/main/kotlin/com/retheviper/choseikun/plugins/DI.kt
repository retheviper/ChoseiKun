package com.retheviper.choseikun.plugins

import com.retheviper.choseikun.domain.service.EventService
import com.retheviper.choseikun.domain.service.ParticipantService
import com.retheviper.choseikun.infrastructure.repository.EventRepository
import com.retheviper.choseikun.infrastructure.repository.ParticipantRepository
import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureDI() {

    val domainModule = module {
        single { EventService(get(), get()) }
        single { ParticipantService(get(), get()) }
    }

    val infrastructureModule = module {
        single { EventRepository() }
        single { ParticipantRepository() }
    }

    install(Koin) {
        slf4jLogger()
        modules(
            listOf(
                domainModule,
                infrastructureModule
            )
        )
    }
}