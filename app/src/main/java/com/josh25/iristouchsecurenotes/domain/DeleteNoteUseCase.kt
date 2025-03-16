package com.josh25.iristouchsecurenotes.domain

import com.josh25.iristouchsecurenotes.data.repository.NoteRepository
import com.josh25.iristouchsecurenotes.ui.theme.NoteModel
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(noteModel: NoteModel) {
        noteRepository.deleteNote(noteModel)
    }
}