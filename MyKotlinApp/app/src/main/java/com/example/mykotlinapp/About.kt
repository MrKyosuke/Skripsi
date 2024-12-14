package com.example.mykotlinapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class About : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.about_activity)

        // Initialize the back button
        val backButton: Button = findViewById(R.id.back_button)

        // Set click listener for the back button
        backButton.setOnClickListener {
            finish() // Close the About activity and return to the previous screen
        }
    }
}
