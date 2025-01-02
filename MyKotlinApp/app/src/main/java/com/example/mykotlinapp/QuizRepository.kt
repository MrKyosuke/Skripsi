import com.example.mykotlinapp.QuizData
import com.example.mykotlinapp.QuizQuestion
import com.example.mykotlinapp.R

object QuizRepository {
    private val quizzes = mapOf(
        "turtle_story" to QuizData(
            listOf(
                QuizQuestion(
                    imageResId = R.drawable.rabbit_race,
                    question = "What did the Rabbit ask the Turtle to do?",
                    answers = listOf("Run a race", "Walk together"),
                    correctAnswerIndex = 0,
                    answerImageRes = listOf(R.drawable.rabbit_race, R.drawable.rabbit_met)
                ),
                QuizQuestion(
                    imageResId = R.drawable.rabbit_sleep,
                    question = "What did the Rabbit do during the race?",
                    answers = listOf("He went to sleep", "He Ran quickly"),
                    correctAnswerIndex = 0,
                    answerImageRes = listOf(R.drawable.rabbit_sleep, R.drawable.rabbit_lost)
                )
            )
        ),
        "shepherd_story" to QuizData(
            listOf(
                QuizQuestion(
                    imageResId = R.drawable.shepherd_field,
                    question = "Where is the shepherd?",
                    answers = listOf("In his house", "In the fields", "At a store", "In the barn"),
                    correctAnswerIndex = 1
                ),
//                QuizQuestion(
//                    imageResId = R.drawable.shepherd_lost_sheep,
//                    question = "What did the shepherd lose?",
//                    answers = listOf("His hat", "His flock", "His sheep", "His dog"),
//                    correctAnswerIndex = 2
//                ),
//                QuizQuestion(
//                    imageResId = R.drawable.shepherd_found_sheep,
//                    question = "How did the shepherd feel after finding his sheep?",
//                    answers = listOf("Happy", "Angry", "Tired", "Confused"),
//                    correctAnswerIndex = 0
//                )
            )
        ),
        "elephant_story" to QuizData(
            listOf(
                QuizQuestion(
                    imageResId = R.drawable.elephant_sees_ant,
                    question = "What did the Elephant do when it saw the Ant?",
                    answers = listOf("Ignored", "Helped", "Chased", "Laughed"),
                    correctAnswerIndex = 3
                ),
                QuizQuestion(
                    imageResId = R.drawable.elephant_sees_ant,
                    question = "What is the Ant known for?",
                    answers = listOf("Being lazy", "Being kind and hardworking", "Being strong", "Being small"),
                    correctAnswerIndex = 1
                ),
                QuizQuestion(
                    imageResId = R.drawable.elephant_apologize,
                    question = "How did the Elephant feel after learning its lesson?",
                    answers = listOf("Proud", "Sorry", "Angry", "Happy"),
                    correctAnswerIndex = 1
                )
            )
        )
    )

    fun getQuizData(storyId: String): QuizData {
        return quizzes[storyId] ?: QuizData(emptyList())
    }
}



