import com.example.mykotlinapp.QuizData
import com.example.mykotlinapp.QuizQuestion
import com.example.mykotlinapp.R

object QuizRepository {
    //mapping story datanya sesuai dengan id story yang bakal di implement
    private val quizzes = mapOf(
        "turtle_story" to QuizData(
            listOf(
                QuizQuestion(
                    imageResId = R.drawable.rabbit_met,
                    question = "What did the Rabbit ask the Turtle to do?",
                    answers = listOf("Run a race", "Sleep", "Eat carrots", "Walk together"),
                    correctAnswerIndex = 0
                ),
                QuizQuestion(
                    imageResId = R.drawable.rabbit_race,
                    question = "What did the Rabbit do during the race?",
                    answers = listOf("He went to sleep", "He Ran quickly", "He Gave up", "He Helped the Turtle"),
                    correctAnswerIndex = 0
                ),
                QuizQuestion(
                    imageResId = R.drawable.rabbit_race,
                    question = "Who won the race ?",
                    answers = listOf("Bird", "Rabbit", "Turtle", "Hamster"),
                    correctAnswerIndex = 2
                ),
            )
        ),
        //Shepherd story
        "shepherd_story" to QuizData(
            listOf(
                QuizQuestion(
                    imageResId = R.drawable.shepherd_field,
                    question = "Where is the shepherd?",
                    answers = listOf("In his house", "Somewhere in the world", "At a store", "At the fields"),
                    correctAnswerIndex = 0
                ),
                QuizQuestion(
                    imageResId = R.drawable.rabbit_race,
                    question = "What did the Rabbit do during the race?",
                    answers = listOf("He went to sleep", "He Ran quickly", "He Gave up", "He Helped the Turtle"),
                    correctAnswerIndex = 0
                ),
                QuizQuestion(
                    imageResId = R.drawable.rabbit_race,
                    question = "Who won the race ?",
                    answers = listOf("Bird", "Rabbit", "Turtle", "Hamster"),
                    correctAnswerIndex = 2
                ),
            )
        ),
    )

    //ambil datanya pake ini
    fun getQuizData(storyId: String): QuizData {
        return quizzes[storyId] ?: QuizData(emptyList()) //ini iseng doang in case kalau quiznya tidak ke load, bisa indikasi tidak ada quiz dalam storynya
    }
}


