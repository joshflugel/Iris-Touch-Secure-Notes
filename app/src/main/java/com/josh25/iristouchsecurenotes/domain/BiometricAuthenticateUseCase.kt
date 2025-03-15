package com.josh25.iristouchsecurenotes.domain

import androidx.fragment.app.FragmentActivity
import com.josh25.iristouchsecurenotes.data.auth.BiometricAuthManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthUseCase @Inject constructor(
    private val biometricAuthManager: BiometricAuthManager
) {
    fun authenticate(
        activity: FragmentActivity,
        onSuccess: () -> Unit,
        onFailure: () -> Unit,
        onError: (String) -> Unit
    ) {
        val prompt = biometricAuthManager.createBiometricPrompt(activity, onSuccess, onFailure, onError)
        val promptInfo = biometricAuthManager.getPromptInfo()
        prompt.authenticate(promptInfo)
    }
}
