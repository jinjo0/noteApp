package com.example.noteapp.model

import java.time.Instant
import java.time.LocalDateTime
import java.util.Date
import java.util.UUID

data class Note(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val description: String,
    val entryDate: Date = Date.from(Instant.now())
)
