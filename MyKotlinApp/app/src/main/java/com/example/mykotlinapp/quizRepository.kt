import com.example.mykotlinapp.QuizData
import com.example.mykotlinapp.QuizQuestion
import com.example.mykotlinapp.R

object quizRepository {
    private val quizzes = mapOf(

        "bawang_merah" to QuizData(
            listOf(
                QuizQuestion(
                    imageResId = R.drawable.rabbit_race,
                    question = "What did the Rabbit ask the Turtle to do?",
                    answers = listOf("Run a race", "Walk together"),
                    correctAnswerIndex = 0,
                    answerImageRes = listOf(R.drawable.rabbit_race, R.drawable.rabbit_met),
                    explanations = listOf(
                        "Correct! The Rabbit challenged the Turtle to a race.",
                        "Incorrect! The Rabbit did not ask to walk together, but to run a race."
                    )
                ),
                QuizQuestion(
                    imageResId = R.drawable.rabbit_sleep,
                    question = "What did the Rabbit do during the race?",
                    answers = listOf("He went to sleep", "He ran quickly"),
                    correctAnswerIndex = 0,
                    answerImageRes = listOf(R.drawable.rabbit_sleep, R.drawable.rabbit_lost),
                    explanations = listOf(
                        "Correct! The Rabbit slept during the race, allowing the Turtle to win.",
                        "Incorrect! The Rabbit did not run quickly the entire time. He took a nap!"
                    )
                )
            )
        ),

        "turtle_story" to QuizData(
            listOf(
                QuizQuestion(
                    imageResId = R.drawable.rabbit_race,
                    question = "What did the Rabbit ask the Turtle to do?",
                    answers = listOf("Run a race", "Walk together"),
                    correctAnswerIndex = 0,
                    answerImageRes = listOf(R.drawable.rabbit_race, R.drawable.rabbit_met),
                    explanations = listOf(
                        "Correct! The Rabbit challenged the Turtle to a race.",
                        "Incorrect! The Rabbit did not ask to walk together, but to run a race."
                    )
                ),
                QuizQuestion(
                    imageResId = R.drawable.rabbit_sleep,
                    question = "What did the Rabbit do during the race?",
                    answers = listOf("He went to sleep", "He ran quickly"),
                    correctAnswerIndex = 0,
                    answerImageRes = listOf(R.drawable.rabbit_sleep, R.drawable.rabbit_lost),
                    explanations = listOf(
                        "Correct! The Rabbit slept during the race, allowing the Turtle to win.",
                        "Incorrect! The Rabbit did not run quickly the entire time. He took a nap!"
                    )
                )
            )
        ),

        "bawang_merah_easy" to QuizData(
            listOf(
                QuizQuestion(
                    imageResId = R.drawable.bawang_story,
                    question = "Who was kind and hardworking?",
                    answers = listOf("Left", "Right"),
                    answerImageRes = listOf(R.drawable.b_putih, R.drawable.b_merah),
                    correctAnswerIndex = 0,
                    explanations = listOf(
                        "Correct! They went left.",
                        "Incorrect! They went right instead."
                    )
                ),
                QuizQuestion(
                    imageResId = R.drawable.lost_shawl,
                    question = "What did Bawang Putih find while searching for the red shawl?",
                    answers = listOf("Left", "Right"),
                    answerImageRes = listOf(R.drawable.grandma, R.drawable.bawang_mother),
                    correctAnswerIndex = 0,
                    explanations = listOf(
                        "Correct! They went left.",
                        "Incorrect! They went right instead."
                    )
                ),
                QuizQuestion(
                    imageResId = R.drawable.pumpkin_gift,
                    question = "What did Bawang Putih choose as a reward?",
                    answers = listOf("Left", "Right"),
                    answerImageRes = listOf(R.drawable.big_pumpkin, R.drawable.small_pumpkin),
                    correctAnswerIndex = 0,
                    explanations = listOf(
                        "Correct! They went left.",
                        "Incorrect! They went right instead."
                    )
                ),
                QuizQuestion(
                    imageResId = R.drawable.false_pumpkin,
                    question = "What came out of the pumpkin Bawang Putih opened?",
                    answers = listOf("Left", "Right"),
                    answerImageRes = listOf(R.drawable.jewelry_pumpkin, R.drawable.poison_pumpkin),
                    correctAnswerIndex = 0,
                    explanations = listOf(
                        "Correct! They went left.",
                        "Incorrect! They went right instead."
                    )
                )
            )
        ),
        "malin_kundang_easy" to QuizData(
            listOf(
                QuizQuestion(
                    imageResId = R.drawable.malin_leave,
                    question = "Who loved Malin Kundang very much?",
                    answers = listOf("Left", "Right"),
                    answerImageRes = listOf(R.drawable.malin_mom, R.drawable.random_woman),
                    correctAnswerIndex = 0,
                    explanations = listOf(
                        "Correct! They went left.",
                        "Incorrect! They went right instead."
                    )
                ),
                QuizQuestion(
                    imageResId = R.drawable.success_merc,
                    question = "What did Malin Kundang do when he became rich?",
                    answers = listOf("Left", "Right"),
                    answerImageRes = listOf(R.drawable.happy_mom, R.drawable.rejection),
                    correctAnswerIndex = 1,
                    explanations = listOf(
                        "Correct! They went left.",
                        "Incorrect! They went right instead."
                    )
                ),
                QuizQuestion(
                    imageResId = R.drawable.rejection,
                    question = "What did Malin’s mother do when he refused to acknowledge her?",
                    answers = listOf("Left", "Right"),
                    answerImageRes = listOf(R.drawable.curse, R.drawable.smiling_mom),
                    correctAnswerIndex = 0,
                    explanations = listOf(
                        "Correct! They went left.",
                        "Incorrect! They went right instead."
                    )
                ),
                QuizQuestion(
                    imageResId = R.drawable.rejection,
                    question = "What happened to Malin Kundang at the end?",
                    answers = listOf("Left", "Right"),
                    answerImageRes = listOf(R.drawable.the_punishment, R.drawable.success_merc),
                    correctAnswerIndex = 0,
                    explanations = listOf(
                        "Correct! They went left.",
                        "Incorrect! They went right instead."
                    )
                ),
            )
        )

    )

    fun getQuizData(storyId: String): QuizData {
        return quizzes[storyId] ?: QuizData(emptyList())
    }
}



