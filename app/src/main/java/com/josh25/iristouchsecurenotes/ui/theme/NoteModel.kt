package com.josh25.iristouchsecurenotes.ui.theme

import com.josh25.iristouchsecurenotes.data.database.NoteEntity
import java.util.UUID

data class NoteModel(
    val id: String = UUID.randomUUID().toString(),
    val note: String,
)

fun NoteModel.toEntity(): NoteEntity {
    return NoteEntity(this.id, this.note, null)
}