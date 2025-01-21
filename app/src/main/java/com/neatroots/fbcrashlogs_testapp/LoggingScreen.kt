package com.neatroots.fbcrashlogs_testapp

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable


fun LoggingScreen() {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Manual Log Button
            Button(
                onClick = {
                    LoggingUtils.logManualMessage("This is a manual log message")
                    Toast.makeText(context, "Manual log recorded", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text("Manual Log")
            }

            // Crash Log Button
            Button(
                onClick = {
                    Toast.makeText(context, "App will crash for test log", Toast.LENGTH_SHORT).show()
                    throw RuntimeException("Test crash for Crash Log") // This will crash the app
                },
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text("Crash Log")
            }

            // Exception Handling Log Button
            Button(
                onClick = {
                    try {
                        val result = 10 / 0 // This will throw an ArithmeticException
                    } catch (e: Exception) {
                        LoggingUtils.logException(e)
                        Toast.makeText(context, "Exception log recorded", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text("Exception Handling Log")
            }

            // Initialization Log Button
            Button(
                onClick = {
                    LoggingUtils.logInitializationError("Initialization error: Database connection failed")
                    Toast.makeText(context, "Initialization error log recorded", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text("Initialization Log")
            }
        }
    }
}