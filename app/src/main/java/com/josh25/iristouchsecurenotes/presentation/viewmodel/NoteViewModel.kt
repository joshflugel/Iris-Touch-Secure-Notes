package com.josh25.iristouchsecurenotes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josh25.iristouchsecurenotes.ui.theme.NoteModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.josh25.iristouchsecurenotes.domain.AddNoteUseCase
import com.josh25.iristouchsecurenotes.domain.DeleteNoteUseCase
import com.josh25.iristouchsecurenotes.domain.GetDecryptedNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val addNoteUseCase: AddNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val getDecryptedNotesUseCase: GetDecryptedNotesUseCase
) : ViewModel() {

    private val _notes = MutableStateFlow<List<NoteModel>>(emptyList())
    val notes: StateFlow<List<NoteModel>> = _notes

    private val _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog

    init {
        viewModelScope.launch {
            getDecryptedNotesUseCase().collect { decryptedNotes ->
                _notes.value = decryptedNotes
            }
        }
    }

    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onNoteCreated(note: String) {
        _showDialog.value = false
        viewModelScope.launch {
            val newNote = NoteModel(note = note)
            addNoteUseCase(newNote)
        }
    }

    fun onShowDialogClick() {
        _showDialog.value = true
    }

    fun onNoteDeleted(noteModel: NoteModel) {
        viewModelScope.launch {
            deleteNoteUseCase(noteModel)
        }
    }
}