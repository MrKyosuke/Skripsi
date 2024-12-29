package com.example.mykotlinapp

import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EasyQuiz : AppCompatActivity() {

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

        val storyId = intent.getStringExtra("story_id")
        if (storyId.isNullOrEmpty()) {
            Toast.makeText(this, "Invalid Story ID", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        quizData = QuizRepository.getQuizData(storyId)
        if (quizData.questions.isEmpty()) {
            Toast.makeText(this, "No quiz data available for this story", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Initialize views
        questionTv = findViewById(R.id.question_tv)
        micButton = findViewById(R.id.btn_voice_input)
        storyImage = findViewById(R.id.story_image)
        answerAImage = findViewById(R.id.answer_a_image)
        answerBImage = findViewById(R.id.answer_b_image)

        loadCurrentQuestion()

        // Voice Input pakai ini
        micButton.setOnClickListener {
            promptForSpeech()
        }
    }

    private fun loadCurrentQuestion() {
        val currentQuestion = quizData.questions.getOrNull(currentQuestionIndex)

        currentQuestion?.let {
            questionTv.text = it.question
            storyImage.setImageResource(it.imageResId)

            val images = it.answerImageRes
            if (images != null && images.size >= 2) {
                answerAImage.setImageResource(images[0])
                answerBImage.setImageResource(images[1])
            } else {
                Toast.makeText(this, "No images available for this question!", Toast.LENGTH_SHORT).show()
                answerAImage.setImageDrawable(null) // ini masih belum mau jalan, coba testing ulang
                answerBImage.setImageDrawable(null)
            }
        } ?: run {
            Toast.makeText(this, "Invalid Question Data !", Toast.LENGTH_SHORT).show()
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
            val spokenText = result?.get(0)?.toLowerCase() ?: return
            checkAnswer(spokenText)
        }
    }

    private fun checkAnswer(spokenText: String) {
        val currentQuestion = quizData.questions.getOrNull(currentQuestionIndex) ?: return
        val answers = currentQuestion.answers.map { it.toLowerCase() }

        val correctAnswerIndex = currentQuestion.correctAnswerIndex
        val correctAnswer = answers[correctAnswerIndex]

        if (spokenText.contains(correctAnswer, ignoreCase = true)) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
            proceedToNextQuestion()
        } else {
            Toast.makeText(this, "Incorrect, Try again!", Toast.LENGTH_SHORT).show()
        }
    }


    private fun proceedToNextQuestion() {
        currentQuestionIndex++

        if (currentQuestionIndex < quizData.questions.size) {
            loadCurrentQuestion()
        } else {
            Toast.makeText(this, "Quiz completed!", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}
