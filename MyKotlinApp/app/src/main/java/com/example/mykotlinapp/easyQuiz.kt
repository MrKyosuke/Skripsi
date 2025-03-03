package com.example.mykotlinapp

import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class easyQuiz : AppCompatActivity() {

    private val REQ_CODE_SPEECH_INPUT = 100

    private lateinit var questionTv: TextView
    private lateinit var micButton: ImageButton
    private lateinit var storyImage: ImageView
    private lateinit var answerAImage: ImageView
    private lateinit var answerBImage: ImageView

    private lateinit var quizData: QuizData
    private var currentQuestionIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz_activity_easy)

        // Get story ID from intent
        val storyId = intent.getStringExtra("story_id") ?: run {
            Toast.makeText(this, "Invalid Story ID", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Get quiz data
        quizData = quizRepository.getQuizData(storyId)
        if (quizData.questions.isEmpty()) {
            Toast.makeText(this, "No quiz data available!", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Initialize UI elements
        questionTv = findViewById(R.id.question_tv)
        micButton = findViewById(R.id.btn_voice_input)
        storyImage = findViewById(R.id.story_image)
        answerAImage = findViewById(R.id.answer_a_image)
        answerBImage = findViewById(R.id.answer_b_image)

        // Load first question
        loadCurrentQuestion()

        // Set up voice input button
        micButton.setOnClickListener {
            promptForSpeech()
        }
    }

    private fun loadCurrentQuestion() {
        val question = quizData.questions.getOrNull(currentQuestionIndex) ?: return

        questionTv.text = question.question
        storyImage.setImageResource(question.imageResId)

        val images = question.answerImageRes
        if (!images.isNullOrEmpty() && images.size >= 2) {
            answerAImage.setImageResource(images[0])
            answerBImage.setImageResource(images[1])
            answerAImage.visibility = View.VISIBLE
            answerBImage.visibility = View.VISIBLE
        } else {
            Log.w("EasyQuiz", "Not enough images found!")
            answerAImage.visibility = View.GONE
            answerBImage.visibility = View.GONE
        }
    }



    private fun promptForSpeech() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say your answer!")
        startActivityForResult(intent, REQ_CODE_SPEECH_INPUT)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQ_CODE_SPEECH_INPUT && resultCode == RESULT_OK) {
            val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            val spokenText = result?.get(0)?.lowercase() ?: return
            checkAnswer(spokenText)
        }
    }

    private fun checkAnswer(spokenText: String) {
        val question = quizData.questions.getOrNull(currentQuestionIndex) ?: return
        val correctAnswer = question.answers[question.correctAnswerIndex].lowercase()

        if (spokenText.contains(correctAnswer, ignoreCase = true)) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
            proceedToNextQuestion()
        } else {
            Toast.makeText(this, "Incorrect, try again!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun finishQuiz() {
        // Navigate to the Story Selection Menu
        val intent = Intent(this, easyLevel::class.java)
        startActivity(intent)
        finish() // Ensure to finish the quiz activity after navigating
    }

    private fun proceedToNextQuestion() {
        currentQuestionIndex++
        if (currentQuestionIndex < quizData.questions.size) {
            loadCurrentQuestion()
        } else {
            Toast.makeText(this, "Quiz completed!", Toast.LENGTH_LONG).show()
            finishQuiz()
        }
    }
}
