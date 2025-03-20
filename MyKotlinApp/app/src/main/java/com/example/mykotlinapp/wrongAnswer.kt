package com.example.mykotlinapp

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class wrongAnswer : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wrong_answer)

        val explanationTextView = findViewById<TextView>(R.id.explanationTextView)
        val wrongAnswerImageView = findViewById<ImageView>(R.id.wrongAnswerImageView)
        val descriptionTextView = findViewById<TextView>(R.id.descriptionTextView)
        val backButton = findViewById<Button>(R.id.backButton)

        val explanation = intent.getStringExtra("explanation") ?: "Incorrect answer."
        val description = intent.getStringExtra("description") ?: "Try again!"
        val imageResId = intent.getIntExtra("imageResId", 0)

        explanationTextView.text = explanation
        descriptionTextView.text = description
        wrongAnswerImageView.setImageResource(imageResId)

        backButton.setOnClickListener {
            finish()
        }
    }
}

