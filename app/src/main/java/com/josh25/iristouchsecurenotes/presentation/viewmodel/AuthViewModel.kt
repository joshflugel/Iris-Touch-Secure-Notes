package com.josh25.iristouchsecurenotes.presentation.viewmodel

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.josh25.iristouchsecurenotes.domain.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {

    private val _authState = MutableStateFlow(false)
    val authState: StateFlow<Boolean> = _authState

    fun authenticate(activity: FragmentActivity) {
        authUseCase.authenticate(
            activity,
            onSuccess = { _authState.value = true },
            onFailure = { _authState.value = false },
            onError = { _authState.value = false }
        )
    }
}