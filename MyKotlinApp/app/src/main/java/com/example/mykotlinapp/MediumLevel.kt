package com.example.mykotlinapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MediumLevel : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medium_level)

        // Back button functionality
        val backButton = findViewById<ImageButton>(R.id.button_back)
        backButton.setOnClickListener {
            finish() // Closes the current activity and returns to the previous one
        }

        // Turtle and Rabbit button functionality
        val buttonBawang = findViewById<ImageButton>(R.id.button_bawang)
        buttonBawang.setOnClickListener {
            val intent = Intent(this, Bawang_Merah::class.java)
            startActivity(intent)
        }

        // Turtle and Rabbit button functionality
        val buttonShepherd = findViewById<ImageButton>(R.id.button_easy_story)
        buttonShepherd.setOnClickListener {
            // Navigate to the Turtle and Rabbit story
            val intent = Intent(this, Shepherd_Story::class.java)
            startActivity(intent)
        }
    }
}

