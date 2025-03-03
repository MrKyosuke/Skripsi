package com.example.mykotlinapp

object wrongAnswerRepository {

    data class WrongAnswerData(
        val imageResId: Int,  // Drawable resource ID
        val explanation: String,
        val description: String
    )

    private val wrongAnswers = mapOf(
        "turtle_story" to mapOf(
            0 to mapOf(
                "Walk together" to WrongAnswerData(
                    imageResId = R.drawable.rabbit_met,
                    explanation = "Incorrect! The Rabbit did not ask to walk together.",
                    description = "The Rabbit was confident and underestimated the Turtle."
                )
            ),
            1 to mapOf(
                "He ran quickly" to WrongAnswerData(
                    imageResId = R.drawable.rabbit_lost,
                    explanation = "Incorrect! The Rabbit did not run quickly the entire time. He took a nap!",
                    description = "The Rabbit was overconfident and decided to rest, which cost him the race."
                )
            )
        ),
        "shepherd_story" to mapOf(
            0 to mapOf(
                "In his house" to WrongAnswerData(
                    imageResId = R.drawable.shepherd_field,
                    explanation = "Incorrect! The shepherd is not at home; he is in the fields.",
                    description = "The shepherd was watching his sheep in the fields."
                ),
                "At a store" to WrongAnswerData(
                    imageResId = R.drawable.shepherd_field,
                    explanation = "Incorrect! The shepherd is not at a store, but in the fields.",
                    description = "Shepherds usually stay with their sheep in open fields."
                ),
                "In the barn" to WrongAnswerData(
                    imageResId = R.drawable.shepherd_field,
                    explanation = "Incorrect! The shepherd is not in the barn; he is in the fields.",
                    description = "Shepherds rarely stay in barns; they guide their sheep in open fields."
                )
            )
        ),
        "elephant_story" to mapOf(
            0 to mapOf(
                "Ignored" to WrongAnswerData(
                    imageResId = R.drawable.elephant_shoots_water,
                    explanation = "Incorrect! The Elephant did not ignore the Ant; it laughed at it.",
                    description = "The Elephant found the Ant amusing and made fun of it."
                ),
                "Helped" to WrongAnswerData(
                    imageResId = R.drawable.elephant_sees_ant,
                    explanation = "Incorrect! The Elephant did not help the Ant; it made fun of it.",
                    description = "Instead of helping, the Elephant underestimated the Ant."
                ),
                "Chased" to WrongAnswerData(
                    imageResId = R.drawable.elephant_apologize,
                    explanation = "Incorrect! The Elephant did not chase the Ant; it laughed at it.",
                    description = "The Elephant saw the Ant as too small to be a threat."
                )
            )
        )
    )

    fun getWrongAnswer(storyId: String, questionIndex: Int, selectedAnswer: String): WrongAnswerData? {
        val possibleAnswers = wrongAnswers[storyId]?.get(questionIndex)
        return possibleAnswers?.entries?.find { it.key.lowercase() == selectedAnswer.lowercase() }?.value
    }
}
