package com.retheviper.choseikun.plugins

import com.retheviper.choseikun.infrastructure.table.EventCandidateTable
import com.retheviper.choseikun.infrastructure.table.EventTable
import com.retheviper.choseikun.infrastructure.table.ParticipantAvailabilityTable
import com.retheviper.choseikun.infrastructure.table.ParticipantTable
import io.ktor.server.application.Application
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDatabases() {
    Database.connect(
        url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
        user = "root",
        driver = "org.h2.Driver",
        password = ""
    )

    transaction {
        SchemaUtils.create(
            EventTable,
            EventCandidateTable,
            ParticipantTable,
            ParticipantAvailabilityTable
        )
    }
}
