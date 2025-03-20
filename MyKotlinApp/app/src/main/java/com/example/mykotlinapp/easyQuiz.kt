package com.example.mykotlinapp

import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mykotlinapp.wrongAnswerRepositorMedium

class easyQuiz : AppCompatActivity() {

    private lateinit var speechRecognizer: SpeechRecognizer
    private lateinit var questionTv: TextView
    private lateinit var micButton: ImageButton
    private lateinit var storyImage: ImageView
    private lateinit var answerAImage: ImageView
    private lateinit var answerBImage: ImageView
    private lateinit var wrongAnswerRepositoryMedium: wrongAnswerRepositorMedium
    private lateinit var quizData: QuizDataEasy
    private var currentQuestionIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz_activity_easy)
        wrongAnswerRepositoryMedium = wrongAnswerRepositorMedium

        val storyId = intent.getStringExtra("story_id") ?: run {
            Toast.makeText(this, "Invalid Story ID", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        quizData = quizRepository.getEasyQuizData(storyId) ?: run {
            Toast.makeText(this, "No quiz data available!", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        questionTv = findViewById(R.id.question_tv)
        micButton = findViewById(R.id.btn_voice_input)
        storyImage = findViewById(R.id.story_image)
        answerAImage = findViewById(R.id.answer_a_image)
        answerBImage = findViewById(R.id.answer_b_image)

        loadCurrentQuestion()

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)
        speechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(params: Bundle?) {
                Toast.makeText(this@easyQuiz, "Listening...", Toast.LENGTH_SHORT).show()
            }
            override fun onBeginningOfSpeech() {}
            override fun onRmsChanged(rmsdB: Float) {}
            override fun onBufferReceived(buffer: ByteArray?) {}
            override fun onEndOfSpeech() {}
            override fun onError(error: Int) {
                Toast.makeText(this@easyQuiz, "Couldn't recognize speech, try again!", Toast.LENGTH_SHORT).show()
            }
            override fun onResults(results: Bundle?) {
                val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                val spokenText = matches?.get(0)?.lowercase() ?: return
                checkAnswer(spokenText)
            }
            override fun onPartialResults(partialResults: Bundle?) {}
            override fun onEvent(eventType: Int, params: Bundle?) {}
        })

        micButton.setOnClickListener {
            startListening()
        }
    }

    private fun startListening() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1)
        speechRecognizer.startListening(intent)
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

    private fun checkAnswer(spokenText: String) {
        val storyId = intent.getStringExtra("story_id") ?: return
        val easyQuizData = quizRepository.getEasyQuizData(storyId) ?: return
        val currentQuestion = easyQuizData.questions.getOrNull(currentQuestionIndex) ?: return

        val correctAnswer = currentQuestion.answers[currentQuestion.correctAnswerIndex]
        val normalizedSpokenText = spokenText.trim().lowercase()
        val normalizedCorrectAnswer = correctAnswer.trim().lowercase()

        Log.d("EasyQuizActivity", "User Answer: '$normalizedSpokenText'")
        Log.d("EasyQuizActivity", "Correct Answer: '$normalizedCorrectAnswer'")

        if (normalizedSpokenText == normalizedCorrectAnswer) {
            Toast.makeText(this, "Nice One!", Toast.LENGTH_SHORT).show()
            proceedToNextQuestion()
        } else {
            handleWrongAnswer(normalizedSpokenText)
        }
    }

    private fun handleWrongAnswer(userAnswer: String) {
        val storyId = intent.getStringExtra("story_id") ?: ""
        val wrongAnswerData = wrongAnswerRepositoryMedium.getWrongAnswer(storyId, currentQuestionIndex, userAnswer)

        if (wrongAnswerData != null) {
            val intent = Intent(this, wrongAnswer::class.java).apply {
                putExtra("explanation", wrongAnswerData.explanation)
                putExtra("imageResId", wrongAnswerData.imageResId)
                putExtra("description", wrongAnswerData.description)
            }
            startActivity(intent)
        } else {
            Toast.makeText(this, "Wrong answer but no explanation available.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun finishQuiz() {
        val intent = Intent(this, easyLevel::class.java)
        startActivity(intent)
        finish()
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

    override fun onDestroy() {
        super.onDestroy()
        speechRecognizer.destroy()
    }
}