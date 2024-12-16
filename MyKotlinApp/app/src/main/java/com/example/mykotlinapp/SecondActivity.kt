package com.example.mykotlinapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class SecondActivity : AppCompatActivity(), View.OnClickListener {
    private val REQUEST_CODE_SPEECH_INPUT = 102

    // Quiz elements
    lateinit var questionTv: TextView
    lateinit var answerATv: TextView
    lateinit var answerBTv: TextView
    lateinit var answerCTv: TextView
    lateinit var btnVoiceInput: Button
    lateinit var btnBack: Button

    // Kata kunci yang akan dikenal device untuk correct answer
    private val correctAnswer: String = "mom"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_page)

        // Link UI elements
        questionTv = findViewById(R.id.question_tv)
        answerATv = findViewById(R.id.answer_a_tv)
        answerBTv = findViewById(R.id.answer_b_tv)
        answerCTv = findViewById(R.id.answer_c_tv)
        btnVoiceInput = findViewById(R.id.btn_voice_input)
        btnBack = findViewById(R.id.btn_back)

        // Button Voice
        btnVoiceInput.setOnClickListener(this)

        // Button back untuk kembali ke halaman sebelumnya
        btnBack.setOnClickListener {
            finish()
        }
    }

    // Handle voice input button click
    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_voice_input) {
            startVoiceInput()
        }
    }

    // Format dan metode Voice Input
    private fun startVoiceInput() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Please answer the question!")

        try {
            startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT)
        } catch (e: Exception) {
            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    // Proses voice input
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_SPEECH_INPUT && resultCode == Activity.RESULT_OK && data != null) {
            val result: ArrayList<String> = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS) as ArrayList<String>
            val userAnswer = result[0].toLowerCase(Locale.getDefault())

            // kalau benar akan kasih pop up berikut
            if (userAnswer.contains(correctAnswer)) {
                Toast.makeText(this, "Atta boy!", Toast.LENGTH_SHORT).show()
                // // kalau salah akan kasih pop up berikut
            } else {
                Toast.makeText(this, "That's not right !", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
