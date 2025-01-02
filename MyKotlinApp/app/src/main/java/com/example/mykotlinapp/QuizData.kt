package com.example.mykotlinapp

data class QuizData(
    val questions: List<QuizQuestion>
)

data class QuizQuestion(
    val question: String,
    val imageResId: Int, // Ini masih testing, tabi coba ditest ulang untuk panggil ID dari panggil image
    val answers: List<String>, // Apparantely ini untuk manggil String tiap Quiz data dalam Medium mode, yang berarti hanya untuk tiap Text yang akan muncul dari Question yang dimasukan di Repository
    val correctAnswerIndex: Int, // Store data answer dari sini, dalam bentuk Int karena akan store option yang dipilih User yang mana
    val answerImageRes: List<Int>? = null // ini untuk value dari resolution image yang ingin ditampilkan
)






