package com.example.mykotlinapp

import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.view.View

class Quiz : AppCompatActivity() {

    private val REQ_CODE_SPEECH_INPUT = 100

    private lateinit var questionTv: TextView
    private lateinit var answerATv: TextView
    private lateinit var answerBTv: TextView
    private lateinit var answerCTv: TextView
    private lateinit var answerDTv: TextView
    private lateinit var micButton: ImageButton
    private lateinit var storyImage: ImageView

    private lateinit var quizData: QuizData
    private var currentQuestionIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz_activity)

        // karena sistemnya sekarang pakai id untuk indikasi story yg ingin di-display, quiznya juga bakal adjust sesuai dengan story yg dipilih user
        val storyId = intent.getStringExtra("story_id") ?: return

        // import dari QuizRepository untuk ambil quiz yg ingin di-display sesuai dengan story_idnya
        quizData = quizRepository.getQuizData(storyId)

        // tampilin view quiznya dari sini, kalau ingin diganti bisa dari quiz_activity.xml
        questionTv = findViewById(R.id.question_tv)
        answerATv = findViewById(R.id.answer_a_btn) as TextView
        answerBTv = findViewById(R.id.answer_b_btn) as TextView
        answerCTv = findViewById(R.id.answer_c_btn) as TextView
        answerDTv = findViewById(R.id.answer_d_btn) as TextView
        micButton = findViewById(R.id.btn_voice_input)
        storyImage = findViewById(R.id.story_image)

        // ini masih testing tapi seharusnya langsung load dari urutan data pertama di Repositornya
        loadCurrentQuestion()

        // Mic button to start voice input
        micButton.setOnClickListener {
            promptForSpeech()
        }
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

            // Hide answer text views if there are fewer options
            answerCTv.visibility = if (answers.size > 2) View.VISIBLE else View.GONE
            answerDTv.visibility = if (answers.size > 3) View.VISIBLE else View.GONE
        }
    }

    // seperti yg versi sebelumnya, bakal pakai speech pop up google, tapi bisa diubah messagenya
    private fun promptForSpeech() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say your answer !")
        //untuk activityforresultnya msh g tau bisa jalan terus tanpa issue atau tidak jadi mungkin bisa di-research kembali kalau ada metode yg bsa dipakai untuk fix atau ada metode lebih baik
        startActivityForResult(intent, REQ_CODE_SPEECH_INPUT)
    }

    // result dari speechnya bakal di story dari metode ini dalam bentuk string dan untuk sekarang resultnya akan selalu dalam bentuk lowercase
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQ_CODE_SPEECH_INPUT && resultCode == RESULT_OK) {
            val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            val spokenText = result?.get(0)?.toLowerCase() ?: return
            checkAnswer(spokenText)
        }
    }

    //
    private fun checkAnswer(spokenText: String) {
        val currentQuestion = quizData.questions.getOrNull(currentQuestionIndex) ?: return
        val answers = currentQuestion.answers.map { it.lowercase() }

        val correctAnswerIndex = currentQuestion.correctAnswerIndex
        if (spokenText.contains(answers[correctAnswerIndex])) {
            Toast.makeText(this, "Nice One!", Toast.LENGTH_SHORT).show()
            proceedToNextQuestion()
        } else {
            val wrongAnswerData = wrongAnswerRepository.getWrongAnswer(
                storyId = intent.getStringExtra("story_id") ?: "",
                questionIndex = currentQuestionIndex,
                selectedAnswer = spokenText
            )

            wrongAnswerData?.let { data ->
                val intent = Intent(this, wrongAnswer::class.java).apply {
                    putExtra("explanation", data.explanation)
                    putExtra("imageResId", data.imageResId)
                    putExtra("description", data.description)
                }
                startActivity(intent)
            }
        }
    }

    // ✅ Now it's outside `checkAnswer()` and can be accessed globally in the class
    private fun proceedToNextQuestion() {
        currentQuestionIndex++

        if (currentQuestionIndex < quizData.questions.size) {
            loadCurrentQuestion()
        } else {
            Toast.makeText(this, "Story Completed !", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}
