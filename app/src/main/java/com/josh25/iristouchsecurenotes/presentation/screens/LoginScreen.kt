package com.josh25.iristouchsecurenotes.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.fragment.app.FragmentActivity
import com.josh25.iristouchsecurenotes.presentation.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    onAuthenticated: () -> Unit
) {
    val viewModel: AuthViewModel = hiltViewModel()
    val isAuthenticated by viewModel.authState.collectAsState()
    val context = LocalContext.current as FragmentActivity

    LaunchedEffect(isAuthenticated) {
        if (isAuthenticated) {
            onAuthenticated()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { viewModel.authenticate(context) }) {
            Text("Unlock Notes")
        }
    }
}
