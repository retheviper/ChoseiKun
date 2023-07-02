package com.retheviper.choseikun.infrastructure.table

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.javatime.datetime

object EventTable : UUIDTable("events") {
    val name = varchar("name", 255)
    val description = varchar("description", 255)
    val created = datetime("created")
    val updated = datetime("updated")
}