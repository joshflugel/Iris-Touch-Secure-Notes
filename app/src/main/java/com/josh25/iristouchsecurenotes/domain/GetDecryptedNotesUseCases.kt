package com.josh25.iristouchsecurenotes.domain

import com.josh25.iristouchsecurenotes.data.repository.NoteRepository
import com.josh25.iristouchsecurenotes.ui.theme.NoteModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDecryptedNotesUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    operator fun invoke(): Flow<List<NoteModel>> {
        return noteRepository.observeDecryptedNotes()
    }
}