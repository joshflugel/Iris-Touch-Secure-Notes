package com.josh25.iristouchsecurenotes.presentation.viewmodel


import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(): ViewModel() {

    private val _authState = MutableStateFlow(false)
    val authState: StateFlow<Boolean> = _authState

    fun setAuthenticated(success: Boolean) {
        _authState.value = success
    }
}

