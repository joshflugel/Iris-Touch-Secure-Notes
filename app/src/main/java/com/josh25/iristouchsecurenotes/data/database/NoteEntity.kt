package com.josh25.iristouchsecurenotes.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NoteEntity")
data class NoteEntity (
    @PrimaryKey
    val id: String,
    val note:String,
    val iv: String? = null
)