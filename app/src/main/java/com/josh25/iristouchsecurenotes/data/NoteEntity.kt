package com.josh25.iristouchsecurenotes.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NoteEntity")
data class NoteEntity (
    @PrimaryKey
    val id: Int,
    val note:String,
    val voiceNotePath: String? = null
)