package com.example.mykotlinapp

data class QuizData(
    override val questions: List<QuizQuestion>
) : IQuizData

data class QuizQuestion(
    val question: String,
    val imageResId: Int,
    val answers: List<String>,
    val correctAnswerIndex: Int
)
