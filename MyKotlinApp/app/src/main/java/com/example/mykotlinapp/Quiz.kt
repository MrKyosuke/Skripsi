package com.example.mykotlinapp

import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import quizRepository
import com.example.mykotlinapp.wrongAnswerRepositorMedium

class Quiz : AppCompatActivity() {

    private lateinit var speechRecognizer: SpeechRecognizer
    private lateinit var questionTv: TextView
    private lateinit var answerATv: TextView
    private lateinit var answerBTv: TextView
    private lateinit var answerCTv: TextView
    private lateinit var answerDTv: TextView
    private lateinit var micButton: ImageButton
    private lateinit var storyImage: ImageView
    private lateinit var wrongAnswerRepositoryMedium: wrongAnswerRepositorMedium
    private lateinit var quizData: QuizData
    private var currentQuestionIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz_activity)

        val storyId = intent.getStringExtra("story_id") ?: return
        quizData = quizRepository.getQuizData(storyId) as QuizData
        wrongAnswerRepositoryMedium = wrongAnswerRepositorMedium

        questionTv = findViewById(R.id.question_tv)
        answerATv = findViewById(R.id.answer_a_btn) as TextView
        answerBTv = findViewById(R.id.answer_b_btn) as TextView
        answerCTv = findViewById(R.id.answer_c_btn) as TextView
        answerDTv = findViewById(R.id.answer_d_btn) as TextView
        micButton = findViewById(R.id.btn_voice_input)
        storyImage = findViewById(R.id.story_image)

        loadCurrentQuestion()

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)
        speechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(params: Bundle?) {
                Toast.makeText(this@Quiz, "Listening...", Toast.LENGTH_SHORT).show()
            }
            override fun onBeginningOfSpeech() {}
            override fun onRmsChanged(rmsdB: Float) {}
            override fun onBufferReceived(buffer: ByteArray?) {}
            override fun onEndOfSpeech() {}
            override fun onError(error: Int) {
                Toast.makeText(this@Quiz, "Couldn't recognize speech, try again!", Toast.LENGTH_SHORT).show()
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
        val currentQuestion = quizData.questions.getOrNull(currentQuestionIndex)
        currentQuestion?.let {
            questionTv.text = it.question
            storyImage.setImageResource(it.imageResId)

            val answers = it.answers
            answerATv.text = answers.getOrNull(0) ?: ""
            answerBTv.text = answers.getOrNull(1) ?: ""
            answerCTv.text = answers.getOrNull(2) ?: ""
            answerDTv.text = answers.getOrNull(3) ?: ""

            answerCTv.visibility = if (answers.size > 2) View.VISIBLE else View.GONE
            answerDTv.visibility = if (answers.size > 3) View.VISIBLE else View.GONE
        }
    }

    private fun checkAnswer(spokenText: String) {
        val currentQuestion = quizData.questions.getOrNull(currentQuestionIndex) ?: return
        val correctAnswer = currentQuestion.answers[currentQuestion.correctAnswerIndex]

        val normalizedSpokenText = spokenText.trim().lowercase()
        val normalizedCorrectAnswer = correctAnswer.trim().lowercase()

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

    private fun proceedToNextQuestion() {
        currentQuestionIndex++
        if (currentQuestionIndex < quizData.questions.size) {
            loadCurrentQuestion()
        } else {
            Toast.makeText(this, "Story Completed !", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        speechRecognizer.destroy()
    }
}
