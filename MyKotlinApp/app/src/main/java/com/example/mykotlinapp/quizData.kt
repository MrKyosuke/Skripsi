package com.example.mykotlinapp

data class QuizData(
    val questions: List<QuizQuestion>
)

data class QuizQuestion(
    val question: String,
    val imageResId: Int,
    val answers: List<String>,
    val correctAnswerIndex: Int,
)







