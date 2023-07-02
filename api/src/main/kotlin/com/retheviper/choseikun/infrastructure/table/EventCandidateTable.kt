package com.retheviper.choseikun.infrastructure.table

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.javatime.datetime

object EventCandidateTable : UUIDTable("event_candidates") {
    val eventId = reference("event_id", EventTable, onDelete = ReferenceOption.CASCADE)
    val datetime = datetime("datetime")
    val created = datetime("created")
    val updated = datetime("updated")
}