package com.josh25.iristouchsecurenotes.ui.theme

data class NoteModel(
    val id: Int = System.currentTimeMillis().hashCode(),
    val note: String,
    val voiceNotePath: String? = null
)