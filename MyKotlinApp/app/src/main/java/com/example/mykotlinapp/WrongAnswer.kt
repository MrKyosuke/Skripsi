package com.example.mykotlinapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class WrongAnswer : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wrong_answer)

        val explanationTextView = findViewById<TextView>(R.id.explanationTextView)
        val wrongAnswerImageView = findViewById<ImageView>(R.id.wrongAnswerImageView)
        val descriptionTextView = findViewById<TextView>(R.id.descriptionTextView)
        val backButton = findViewById<Button>(R.id.backButton)

        // Retrieve data from intent
        val explanation = intent.getStringExtra("explanation") ?: "Incorrect answer."
        val description = intent.getStringExtra("description") ?: "Try again!"
        val imageResId = intent.getIntExtra("imageResId", 0)

        // Set the data
        explanationTextView.text = explanation
        descriptionTextView.text = description
        wrongAnswerImageView.setImageResource(imageResId)

        backButton.setOnClickListener {
            finish()
        }
    }
}

