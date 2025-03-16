package com.josh25.iristouchsecurenotes.data.repository

import android.util.Log
import com.josh25.iristouchsecurenotes.ui.theme.toEntity
import com.josh25.iristouchsecurenotes.data.database.NoteDao
import com.josh25.iristouchsecurenotes.data.database.NoteEntity
import com.josh25.iristouchsecurenotes.data.encryption.EncryptionUtils
import com.josh25.iristouchsecurenotes.ui.theme.NoteModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepository @Inject constructor(private val noteDao: NoteDao) {

    private val _notes = MutableStateFlow<List<NoteModel>>(emptyList())

    fun observeDecryptedNotes(): Flow<List<NoteModel>> {
        return noteDao.getNotes() // Assuming getNotes() is a Flow from the DAO
            .map { items ->
                items.map {
                    val decryptedNote = EncryptionUtils.decryptData(it.note, it.iv ?: "")
                    NoteModel(
                        it.id,
                        decryptedNote
                    )
                }
            }
    }

    suspend fun addNote(noteModel: NoteModel) {
        val (encryptedData, iv) = EncryptionUtils.encryptData(noteModel.note)
        Log.e("joshtag", "Adding note: EncryptedData: $encryptedData, IV: $iv")
        noteDao.addNote(
            NoteEntity(
                noteModel.id,
                encryptedData,
                iv // Input Vector
            )
        )
    }

    suspend fun updateNote(noteModel: NoteModel) {
        val (encryptedData, iv) = EncryptionUtils.encryptData(noteModel.note)
        noteDao.updateNote(
            NoteEntity(
                noteModel.id,
                encryptedData,
                iv
            )
        )
    }

    suspend fun deleteNote(noteModel: NoteModel) {
        noteDao.deleteNote(noteModel.toEntity())
    }
}