package com.josh25.iristouchsecurenotes.data.repository

import com.josh25.iristouchsecurenotes.data.database.NoteDao
import com.josh25.iristouchsecurenotes.data.database.NoteEntity
import com.josh25.iristouchsecurenotes.ui.theme.NoteModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepository @Inject constructor(private val noteDao: NoteDao){

    // Mapper: transforms of an object from one Layer, adapted to a different Layer
    // NoteModel  <-- Mapper <-- NoteEntity

    val notes: Flow<List<NoteModel>> =
        noteDao.getNotes().map { items -> items.map { NoteModel(it.id, it.note, it.voiceNotePath) } }

    suspend fun addNote(noteModel: NoteModel) {
        noteDao.addNote(noteModel.toEntity())
    }

    suspend fun updateNote(noteModel: NoteModel) {
        noteDao.updateNote(noteModel.toEntity())
    }

    suspend fun deleteNote(noteModel: NoteModel) {
        noteDao.deleteNote(noteModel.toEntity())
    }
}

fun NoteModel.toEntity(): NoteEntity {
    return NoteEntity(this.id, this.note, this.voiceNotePath)
}