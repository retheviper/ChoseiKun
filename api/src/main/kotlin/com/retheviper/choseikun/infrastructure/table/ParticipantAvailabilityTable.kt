package com.retheviper.choseikun.infrastructure.table

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.javatime.datetime

object ParticipantAvailabilityTable : UUIDTable("paticipant_availabilities") {
    val eventCandidateId = reference("event_candidate_id", EventCandidateTable, onDelete = ReferenceOption.CASCADE)
    val participantId = reference("participant_id", ParticipantTable, onDelete = ReferenceOption.CASCADE)
    val availability = varchar("availability", 255)
    val created = datetime("created")
    val updated = datetime("updated")

    init {
        uniqueIndex(eventCandidateId, participantId)
    }
}