package com.josh25.iristouchsecurenotes.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.fragment.app.FragmentActivity
import com.josh25.iristouchsecurenotes.R
import com.josh25.iristouchsecurenotes.presentation.viewmodel.AuthViewModel
import com.josh25.iristouchsecurenotes.ui.theme.JoshGray

@SuppressLint("ContextCastToActivity")
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

    Image(
        painter = painterResource(id = R.drawable.iris1b),
        "Background Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
        )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { viewModel.authenticate(context) },
            colors = ButtonDefaults.buttonColors(
                containerColor = JoshGray,
                contentColor = Color.White
        )) {
            Text("UNLOCK", color = Color.White)
        }
    }
}