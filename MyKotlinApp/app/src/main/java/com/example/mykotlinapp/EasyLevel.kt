package com.example.mykotlinapp

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import android.content.Intent

class EasyLevel : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_easy_level)

        // Back button functionality
        val backButton = findViewById<ImageButton>(R.id.button_back)
        backButton.setOnClickListener {
            finish() // Closes the current activity and returns to the previous one
        }

        // Turtle and Rabbit button functionality
        val buttonTurtle = findViewById<ImageButton>(R.id.button_turtle)
        buttonTurtle.setOnClickListener {
            // Navigate to the Turtle and Rabbit story
            val intent = Intent(this, Turtle_Story::class.java)
            startActivity(intent)
        }

        // Turtle and Rabbit button functionality
        val buttonShepherd = findViewById<ImageButton>(R.id.button_shepherd)
        buttonShepherd.setOnClickListener {
            // Navigate to the Turtle and Rabbit story
            val intent = Intent(this, Shepherd_Story::class.java)
            startActivity(intent)
        }

        // Easy Story button functionality
//        val buttonEasyStory = findViewById<CardView>(R.id.button_easy_story)
//        buttonEasyStory.setOnClickListener {
//            // Navigate to the Easy Story
//            val intent = Intent(this, ShepherdStoryActivity::class.java)
//            startActivity(intent)
//        }
    }
}
