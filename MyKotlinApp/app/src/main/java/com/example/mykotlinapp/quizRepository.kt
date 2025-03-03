import com.example.mykotlinapp.QuizData
import com.example.mykotlinapp.QuizQuestion
import com.example.mykotlinapp.R

object quizRepository {
    private val quizzes = mapOf(

        "bawang_merah" to QuizData(
            listOf(
                QuizQuestion(
                    imageResId = R.drawable.incident,
                    question = "Why did Bawang Putih have to do all the chores at home?",
                    answers = listOf("She wanted to help her family", "Her father told her to", "Her stepmother and stepsister forced her", "She lost a bet"),
                    correctAnswerIndex = 2
                ),
                QuizQuestion(
                    imageResId = R.drawable.lost_shawl,
                    question = "What did Bawang Putih do when she lost the red shawl?",
                    answers = listOf("She went home immediately", "She searched for it and met an old grandmother", "She asked Bawang Merah to help her", "She bought a new shawl to replace it"),
                    correctAnswerIndex = 1
                ),
                QuizQuestion(
                    imageResId = R.drawable.pumpkin_gift,
                    question = "Why did Bawang Putih choose the small pumpkin?",
                    answers = listOf("She thought small things were more valuable", "She followed the grandmother’s advice", "She did not see the big pumpkin", "She was afraid of carrying the big one"),
                    correctAnswerIndex = 1
                ),
                QuizQuestion(
                    imageResId = R.drawable.punishment,
                    question = "What lesson does the story teach us?",
                    answers = listOf("Hard work and kindness bring good rewards", "Bigger things always bring more luck", "Being lazy is better than working hard", "Greed always leads to success"),
                    correctAnswerIndex = 0
            )
            )
        ),

        "malin_kundang" to QuizData(
            listOf(
                QuizQuestion(
                    imageResId = R.drawable.malin_leave,
                    question = "Why did Malin Kundang leave his mother?",
                    answers = listOf("He wanted to explore the world", " He wanted to find a better life and become rich", "He was forced to leave by the villagers", "His mother told him to leave"),
                    correctAnswerIndex = 1
                ),
                QuizQuestion(
                    imageResId = R.drawable.happy_mom,
                    question = "How did Malin Kundang’s mother feel when he returned?",
                    answers = listOf("Angry and disappointed", "Nervous and scared", "Happy and excited", "Indifferent and uninterested"),
                    correctAnswerIndex = 2
                ),
                QuizQuestion(
                    imageResId = R.drawable.rejection,
                    question = "Why did Malin Kundang refuse to acknowledge his mother?",
                    answers = listOf("He was afraid of losing his wealth and status", "He truly forgot about her", "His wife told him to ignore her", "He was in a hurry and didn’t see her"),
                    correctAnswerIndex = 0
                ),
                QuizQuestion(
                    imageResId = R.drawable.the_punishment,
                    question = "What happened to Malin Kundang at the end of the story?",
                    answers = listOf("He became even richer", "He apologized to his mothe", "He was turned into stone by a curse", "He ran away to another village"),
                    correctAnswerIndex = 2
                )
            )
        )
    )

    fun getQuizData(storyId: String): QuizData {
        return quizzes[storyId] ?: QuizData(emptyList())
    }
}



