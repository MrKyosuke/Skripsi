import com.example.mykotlinapp.IQuizData
import com.example.mykotlinapp.QuizData
import com.example.mykotlinapp.QuizQuestion
import com.example.mykotlinapp.QuizQuestionEasy
import com.example.mykotlinapp.QuizDataEasy
import com.example.mykotlinapp.R

object quizRepository {

    // Regular quizzes
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
                    answers = listOf("He wanted to explore the world", "He wanted to find a better life and become rich", "He was forced to leave by the villagers", "His mother told him to leave"),
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
                    answers = listOf("He became even richer", "He apologized to his mother", "He was turned into stone by a curse", "He ran away to another village"),
                    correctAnswerIndex = 2
                )
            )
        )
    )

    // Easy-mode quizzes
    private val easyQuizzes = mapOf(
        "bawang_merah_easy" to QuizDataEasy(
            listOf(
                QuizQuestionEasy(
                    imageResId = R.drawable.bawang_story,
                    question = "Who was kind and hardworking?",
                    answers = listOf("Left", "Right"),
                    answerImageRes = listOf(R.drawable.b_putih, R.drawable.b_merah),
                    correctAnswerIndex = 0
                ),
                QuizQuestionEasy(
                    imageResId = R.drawable.lost_shawl,
                    question = "What did Bawang Putih find while searching for the red shawl?",
                    answers = listOf("Left", "Right"),
                    answerImageRes = listOf(R.drawable.grandma, R.drawable.bawang_mother),
                    correctAnswerIndex = 0
                ),
                QuizQuestionEasy(
                    imageResId = R.drawable.pumpkin_gift,
                    question = "What did Bawang Putih choose as a reward?",
                    answers = listOf("Left", "Right"),
                    answerImageRes = listOf(R.drawable.big_pumpkin, R.drawable.small_pumpkin),
                    correctAnswerIndex = 1
                ),
                QuizQuestionEasy(
                    imageResId = R.drawable.false_pumpkin,
                    question = "What came out of the pumpkin Bawang Putih opened?",
                    answers = listOf("Left", "Right"),
                    answerImageRes = listOf(R.drawable.jewelry_pumpkin, R.drawable.poison_pumpkin),
                    correctAnswerIndex = 0
                )
            )
        ),
        "malin_kundang_easy" to QuizDataEasy(
            listOf(
                QuizQuestionEasy(
                    imageResId = R.drawable.malin_leave,
                    question = "Who loved Malin Kundang very much?",
                    answers = listOf("Left", "Right"),
                    answerImageRes = listOf(R.drawable.malin_mom, R.drawable.random_woman),
                    correctAnswerIndex = 0
                ),
                QuizQuestionEasy(
                    imageResId = R.drawable.success_merc,
                    question = "What did Malin Kundang do when he became rich?",
                    answers = listOf("Left", "Right"),
                    answerImageRes = listOf(R.drawable.happy_mom, R.drawable.rejection),
                    correctAnswerIndex = 1
                ),
                QuizQuestionEasy(
                    imageResId = R.drawable.rejection,
                    question = "What did Malin’s mother do when he refused to acknowledge her?",
                    answers = listOf("Left", "Right"),
                    answerImageRes = listOf(R.drawable.curse, R.drawable.smiling_mom),
                    correctAnswerIndex = 0
                ),
                QuizQuestionEasy(
                    imageResId = R.drawable.rejection,
                    question = "What happened to Malin Kundang at the end?",
                    answers = listOf("Left", "Right"),
                    answerImageRes = listOf(R.drawable.the_punishment, R.drawable.success_merc),
                    correctAnswerIndex = 0
                )
            )
        )
    )

    // Fetch quiz data based on story ID
    fun getQuizData(storyId: String): IQuizData? {
        return quizzes[storyId]
    }

    // Fetch easy quiz data based on story ID
    fun getEasyQuizData(storyId: String): QuizDataEasy? {
        return easyQuizzes[storyId]
    }
}
