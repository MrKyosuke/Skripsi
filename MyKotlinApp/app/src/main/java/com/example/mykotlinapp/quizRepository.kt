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
        "shepherd_story" to QuizData(
            listOf(
                QuizQuestion(
                    imageResId = R.drawable.shepherd_field,
                    question = "Where is the shepherd?",
                    answers = listOf("In his house", "In the fields", "At a store", "In the barn"),
                    correctAnswerIndex = 1,
                    explanations = listOf(
                        "Incorrect! The shepherd is not in his house.",
                        "Correct! The shepherd is in the fields.",
                        "Incorrect! The shepherd is not at a store.",
                        "Incorrect! The shepherd is not in the barn."
                    )
                )
            )
        ),

        "bawang_merah" to QuizData(
            listOf(
                QuizQuestion(
                    imageResId = R.drawable.village_scene,
                    question = "What is the name of the merchants daughter ?",
                    answers = listOf("Red Onion", "White Onion", "Blue Onion", "Black Onion"),
                    correctAnswerIndex = 1,
                    explanations = listOf(
                        "Correct! the name of the Merchants daughter is Bawang Putih.",
                        "Incorrect ! it's not Bawang Merah",
                        "Incorrect ! it's not Bawang Biru",
                        "Incorrect ! it's not Bawang Hitam."
                    )
                )
            )
        ),
    )

    fun getQuizData(storyId: String): QuizData {
        return quizzes[storyId] ?: QuizData(emptyList())
    }
}



