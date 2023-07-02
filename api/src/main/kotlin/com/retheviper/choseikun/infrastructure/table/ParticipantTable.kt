package com.retheviper.choseikun.infrastructure.table

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.javatime.datetime

object ParticipantTable : UUIDTable("participants") {
    val eventId = reference("event_id", EventTable, onDelete = ReferenceOption.CASCADE)
    val name = varchar("name", 255)
    val comment = varchar("comment", 255)
    val created = datetime("created")
    val updated = datetime("updated")
}