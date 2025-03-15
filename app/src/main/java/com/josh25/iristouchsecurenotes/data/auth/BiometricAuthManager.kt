package com.josh25.iristouchsecurenotes.data.auth

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import java.util.concurrent.Executor

class BiometricAuthManager(private val context: Context) {

    private val executor: Executor = ContextCompat.getMainExecutor(context)

    fun createBiometricPrompt(
        onSuccess: () -> Unit,
        onFailure: () -> Unit,
        onError: (String) -> Unit
    ): BiometricPrompt {
        val callback = object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                onSuccess()
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                onFailure()
            }

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                onError(errString.toString())
            }
        }

        val activity = context as? FragmentActivity
            ?: throw IllegalArgumentException("Context must be a FragmentActivity")

        return BiometricPrompt(activity, executor, callback)
    }

    fun getPromptInfo(): BiometricPrompt.PromptInfo {
        return BiometricPrompt.PromptInfo.Builder()
            .setTitle("Authenticate to access Secured Notes")
            .setSubtitle("Use fingerprint, Face, or Iris Scan")
            .setNegativeButtonText("Cancel")
            .build()
    }
}

