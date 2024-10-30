package com.example.quizapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mykotlinapp.R
import java.util.Locale

class SecondActivityActivity : AppCompatActivity() {
    private val REQUEST_CODE_SPEECH_INPUT = 102

    lateinit var questionTv: TextView
    lateinit var answerATv: TextView
    lateinit var answerBTv: TextView
    lateinit var answerCTv: TextView
    lateinit var btnVoiceInput: Button
    lateinit var feedbackTv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_page)

        questionTv = findViewById(R.id.question_tv)
        answerATv = findViewById(R.id.answer_a_tv)
        answerBTv = findViewById(R.id.answer_b_tv)
        answerCTv = findViewById(R.id.answer_c_tv)
        btnVoiceInput = findViewById(R.id.btn_voice_input)
        feedbackTv = findViewById(R.id.feedback_tv)

        // Set up the voice input button
        btnVoiceInput.setOnClickListener {
            startVoiceInput()
        }
    }

    private fun startVoiceInput() {
        if (!SpeechRecognizer.isRecognitionAvailable(this)) {
            Toast.makeText(this, "Speech Recognition is not available", Toast.LENGTH_SHORT).show()
        } else {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Please say your answer (A, B, or C)")
            startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_SPEECH_INPUT && resultCode == Activity.RESULT_OK && data != null) {
            val result: ArrayList<String>? = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            if (result != null && result.isNotEmpty()) {
                val userInput = result[0].toLowerCase(Locale.getDefault())

                when {
                    userInput.contains("a") -> {
                        feedbackTv.text = checkAnswer("A")
                    }
                    userInput.contains("b") -> {
                        feedbackTv.text = checkAnswer("B")
                    }
                    userInput.contains("c") -> {
                        feedbackTv.text = checkAnswer("C")
                    }
                    else -> {
                        feedbackTv.text = "lInvalid input, please say A, B, or C"
                    }
                }
            }
        }
    }

    private fun checkAnswer(userAnswer: String): String {
        // The correct answer is "A: Paris"
        return if (userAnswer == "A") {
            "Correct! The capital of France is Paris."
        } else {
            "Incorrect. The correct answer is A: Paris."
        }
    }
}
