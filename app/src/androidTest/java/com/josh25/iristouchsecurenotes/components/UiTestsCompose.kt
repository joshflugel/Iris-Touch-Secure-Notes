package com.josh25.iristouchsecurenotes.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.josh25.iristouchsecurenotes.presentation.screens.AddNoteDialog
import org.junit.Rule
import org.junit.Test

class UiTestsCompose {

    // fun AddNoteDialog(show: Boolean, onDismiss: () -> Unit, onNoteAdded: (String) -> Unit) {
    // Good design pays off here, better mock lambdas than viewmodels
    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun whenDialogTrue_ThenShowDialog() {
        composeTestRule.setContent {
            AddNoteDialog(true, {}, {})
        }
        composeTestRule.onNodeWithTag("dialog").assertIsDisplayed()
    }

    @Test
    fun whenDialogTrue_ThenSHowDialog() {
        composeTestRule.setContent {
            AddNoteDialog(false, {}, {})
        }
        composeTestRule.onNodeWithTag("dialog").assertDoesNotExist()
    }
}