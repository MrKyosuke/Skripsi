package com.example.mykotlinapp

data class QuizData(
    val questions: List<QuizQuestion>
)

//tipe data dari tiap object di storynya
data class QuizQuestion(
    val imageResId: Int,
    val question: String,
    val answers: List<String>,
    val correctAnswerIndex: Int
)

