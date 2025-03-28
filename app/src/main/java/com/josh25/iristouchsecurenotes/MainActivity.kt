package com.josh25.iristouchsecurenotes

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import androidx.hilt.navigation.compose.hiltViewModel
import com.josh25.iristouchsecurenotes.presentation.screens.LoginScreen
import com.josh25.iristouchsecurenotes.presentation.screens.NotesScreen
import com.josh25.iristouchsecurenotes.presentation.viewmodel.AuthViewModel
import com.josh25.iristouchsecurenotes.presentation.viewmodel.NotesViewModel
import com.josh25.iristouchsecurenotes.ui.theme.IrisTouchSecureNotesTheme
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@AndroidEntryPoint
class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            IrisTouchSecureNotesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    MainApp()
                }
            }
        }
    }
}

@Composable
fun MainApp() {
    var isAuthenticated by remember { mutableStateOf(false) }

    if (isAuthenticated) {
        NotesScreen()
    } else {
        LoginScreen(onAuthenticated = { isAuthenticated = true })
    }
}