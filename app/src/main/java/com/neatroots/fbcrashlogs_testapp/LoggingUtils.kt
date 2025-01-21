package com.neatroots.fbcrashlogs_testapp

import com.google.firebase.crashlytics.FirebaseCrashlytics
import io.sentry.Sentry
import io.sentry.SentryLevel

object LoggingUtils {
    // Initialization Error - Setting to ERROR level
    fun logInitializationError(errorMessage: String) {
        FirebaseCrashlytics.getInstance().log("Initialization Error: $errorMessage")
        FirebaseCrashlytics.getInstance().recordException(Exception(errorMessage))

        // Send to Sentry as ERROR
        Sentry.captureMessage("Initialization Error: $errorMessage", SentryLevel.ERROR)
    }

    // Exception Handling - Capturing exceptions as ERROR by default
    fun logException(e: Exception) {
        FirebaseCrashlytics.getInstance().recordException(e)

        // Send to Sentry as ERROR
        Sentry.captureException(e)
    }

    // Manual Logging
    fun logManualMessage(message: String) {
        FirebaseCrashlytics.getInstance().log("Manual Message: $message")

        // Send to Sentry for visibility
        Sentry.captureMessage(message, SentryLevel.WARNING)
    }

    // Custom Exception - Capturing as ERROR in the Issues tab
    fun logCustomException(message: String) {
        val customException = Exception(message)
        FirebaseCrashlytics.getInstance().recordException(customException)

        // Send to Sentry as ERROR
        Sentry.captureException(customException)
    }

}