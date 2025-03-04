package com.example.mykotlinapp

data class QuizDataEasy(
    override val questions: List<QuizQuestionEasy>
) : IQuizData

data class QuizQuestionEasy(
    val question: String,
    val answerImageRes: List<Int>,
    val imageResId: Int,
    val answers: List<String>,
    val correctAnswerIndex: Int
)
