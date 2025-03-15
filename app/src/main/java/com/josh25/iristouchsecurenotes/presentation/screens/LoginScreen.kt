package com.josh25.iristouchsecurenotes.presentation.screens


import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.josh25.iristouchsecurenotes.data.auth.BiometricAuthManager
import com.josh25.iristouchsecurenotes.presentation.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    onAuthenticated: () -> Unit
) {
    val viewModel: AuthViewModel = viewModel()
    val isAuthenticated by viewModel.authState.collectAsState()

    val context = LocalContext.current
    val biometricAuthManager = BiometricAuthManager(context)
    val biometricPrompt = biometricAuthManager.createBiometricPrompt(
        onSuccess = { viewModel.setAuthenticated(true) },
        onFailure = { viewModel.setAuthenticated(false) },
        onError = { viewModel.setAuthenticated(false) }
    )

    val promptInfo = biometricAuthManager.getPromptInfo()

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
        Button(onClick = { biometricPrompt.authenticate(promptInfo) }) {
            Text("Unlock Notes")
        }
    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    LoginScreen(onAuthenticated = {})
}
