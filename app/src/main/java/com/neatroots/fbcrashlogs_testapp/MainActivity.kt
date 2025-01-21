package com.neatroots.fbcrashlogs_testapp

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.firebase.BuildConfig
import com.google.firebase.Firebase
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.initialize
import io.sentry.Sentry
import io.sentry.android.core.SentryAndroid

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {

                LoggingScreen()
                Firebase.initialize(this)
                FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(!BuildConfig.DEBUG)

        }

        // Initialize Firebase
        Firebase.initialize(this)
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(!BuildConfig.DEBUG)

        // Initialize Sentry
        SentryAndroid.init(this) { options ->
            options.dsn = "https://702e2e92f9a463a68883a29b32ebdaf0@o4508256242434048.ingest.us.sentry.io/4508256248266752" // Replace with your Sentry DSN
            options.tracesSampleRate = 1.0
            options.isDebug = true
        }
        val breakWorld = Button(this).apply {
            text = "Break the world"
            setOnClickListener {
                Sentry.captureException(RuntimeException("This app uses Sentry! :)"))
            }
        }

        addContentView(breakWorld, ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))

    }
}

