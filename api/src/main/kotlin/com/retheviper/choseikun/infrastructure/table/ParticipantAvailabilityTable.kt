package com.retheviper.choseikun.infrastructure.table

import com.retheviper.choseikun.domain.model.Availability
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.javatime.datetime

object ParticipantAvailabilityTable : UUIDTable("participant_availabilities") {
    val eventCandidateId = reference("event_candidate_id", EventCandidateTable, onDelete = ReferenceOption.CASCADE)
    val participantId = reference("participant_id", ParticipantTable, onDelete = ReferenceOption.CASCADE)
    val availability = customEnumeration(
        name = "availability",
        sql = "ENUM('available', 'maybe', 'unavailable', 'unknown')",
        fromDb = { Availability.values()[it as Int] },
        toDb = { it.name })
    val created = datetime("created")
    val updated = datetime("updated")

    init {
        uniqueIndex(eventCandidateId, participantId)
    }
}