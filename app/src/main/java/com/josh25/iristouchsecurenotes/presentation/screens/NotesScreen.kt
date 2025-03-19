package com.josh25.iristouchsecurenotes.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.josh25.iristouchsecurenotes.R
import com.josh25.iristouchsecurenotes.presentation.viewmodel.NotesViewModel
import com.josh25.iristouchsecurenotes.ui.theme.JoshGray
import com.josh25.iristouchsecurenotes.ui.theme.NoteModel

@Composable
fun NotesScreen() {

    val viewModel: NotesViewModel = hiltViewModel()
    val notes by viewModel.notes.collectAsState()
    val showDialog by viewModel.showDialog.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.finger2),
            "Background Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        AddNoteDialog(showDialog, onDismiss = { viewModel.onDialogClose() }, onNoteAdded = { viewModel.onNoteCreated(it) })
        FabDialog(Modifier.align(Alignment.BottomEnd), viewModel)
        NotesList(notes, viewModel)
    }
}

@Composable
fun NotesList(notes: List<NoteModel>, viewModel: NotesViewModel) {
    LazyColumn {
        items(notes, key = { it.id }) { note ->
            NoteItem(note, viewModel)
        }
    }
}

@Composable
fun NoteItem(noteModel: NoteModel, viewModel: NotesViewModel) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .pointerInput(Unit) {
                detectTapGestures(onLongPress = { viewModel.onNoteDeleted(noteModel) })
            },
        //elevation = CardDefaults.cardElevation(4.dp),
        colors = CardColors(
            containerColor = JoshGray,
            contentColor = Color.White,
            disabledContainerColor = JoshGray,
            disabledContentColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(noteModel.note, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun FabDialog(modifier: Modifier, viewModel: NotesViewModel) {
    FloatingActionButton(onClick = { viewModel.onShowDialogClick() }, modifier = modifier.padding(16.dp)) {
        Icon(Icons.Filled.Add, contentDescription = "Add Note")
    }
}

@Composable
fun AddNoteDialog(show: Boolean, onDismiss: () -> Unit, onNoteAdded: (String) -> Unit) {
    var noteContent by rememberSaveable { mutableStateOf("") }
    if (show) {
        Dialog(onDismissRequest = onDismiss) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(JoshGray)
                    .padding(16.dp)
                    .testTag("dialog")
            ) {
                Text(
                    "Add Note",
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.size(16.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = noteContent,
                    onValueChange = { noteContent = it },
                    singleLine = true
                )
                Spacer(modifier = Modifier.size(16.dp))
                Button(
                    onClick = {
                        onNoteAdded(noteContent)
                        noteContent = ""
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Gray,
                        contentColor = Color.White)
                    ) {
                    Text("Add Note")
                }
            }
        }
    }
}
