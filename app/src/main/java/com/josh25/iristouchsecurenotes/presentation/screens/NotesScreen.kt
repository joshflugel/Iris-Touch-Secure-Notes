package com.josh25.iristouchsecurenotes.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josh25.iristouchsecurenotes.R

@Preview
@Composable
fun NotesScreen() {
    Box(Modifier.fillMaxSize()) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.finger2),
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Centered Row with no rounded corners
        Row(
            modifier = Modifier
                .fillMaxWidth() // Ensure the Row spans the entire width
            //    .padding(16.dp) // Add padding inside the Row
                .background(Color(0xB0000000)) // Semi-transparent dark gray background
                .align(Alignment.Center), // Align Row in the center of the screen
            horizontalArrangement = Arrangement.Center // Center the content horizontally inside the Row
        ) {
            Text(
                text = "WELCOME",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)

            )
        }
    }
}
