package com.example.mykotlinapp

import android.util.Log

object wrongAnswerRepositorMedium {

    data class WrongAnswerData(
        val imageResId: Int,
        val explanation: String,
        val description: String
    )

    private val wrongAnswers = mapOf(
        "bawang_merah" to mapOf(
            0 to mapOf(
                "She wanted to help her family" to WrongAnswerData(
                    imageResId = R.drawable.try_again,
                    explanation = "Incorrect! Bawang Putih did not do that to help her family.",
                    description = "Wrong answer unfortunately! Please go back and try again!"
                ),
                "Her father told her to" to WrongAnswerData(
                    imageResId = R.drawable.try_again,
                    explanation = "Incorrect! Bawang Putih's father did not tell her to do that.",
                    description = "Wrong answer unfortunately! Please go back and try again!"
                ),
                "She lost a bet" to WrongAnswerData(
                    imageResId = R.drawable.try_again,
                    explanation = "Incorrect! Bawang Putih did not make any bet.",
                    description = "Wrong answer unfortunately! Please go back and try again!"
                )
            ),
            1 to mapOf(
                "She went home immediately" to WrongAnswerData(
                    imageResId = R.drawable.try_again,
                    explanation = "Incorrect! She did not immediately go home.",
                    description = "Wrong answer unfortunately! Please go back and try again!"
                ),
                "She asked Bawang Merah to help her" to WrongAnswerData(
                    imageResId = R.drawable.try_again,
                    explanation = "Incorrect! She did not ask for Bawang Merah's help.",
                    description = "Wrong answer unfortunately! Please go back and try again!"
                ),
                "She bought a new shawl to replace it" to WrongAnswerData(
                    imageResId = R.drawable.try_again,
                    explanation = "Incorrect! She did not buy a new shawl.",
                    description = "Wrong answer unfortunately! Please go back and try again!"
                )
            ),
            2 to mapOf(
                "She thought small things were more valuable" to WrongAnswerData(
                    imageResId = R.drawable.try_again,
                    explanation = "Incorrect! That is not Bawang Putih's reason.",
                    description = "Wrong answer unfortunately! Please go back and try again!"
                ),
                "She did not see the big pumpkin" to WrongAnswerData(
                    imageResId = R.drawable.try_again,
                    explanation = "Incorrect! She saw both of the pumpkins.",
                    description = "Wrong answer unfortunately! Please go back and try again!"
                ),
                "She was afraid of carrying the big one" to WrongAnswerData(
                    imageResId = R.drawable.try_again,
                    explanation = "Incorrect! That is not Bawang Putih's reason.",
                    description = "Wrong answer unfortunately! Please go back and try again!"
                )
            ),
            3 to mapOf(
                "Bigger things always bring more luck" to WrongAnswerData(
                    imageResId = R.drawable.try_again,
                    explanation = "Incorrect! That is not the moral of the story.",
                    description = "Wrong answer unfortunately! Please go back and try again!"
                ),
                "Being lazy is better than working hard" to WrongAnswerData(
                    imageResId = R.drawable.try_again,
                    explanation = "Incorrect! That is not the moral of the story.",
                    description = "Wrong answer unfortunately! Please go back and try again!"
                ),
                "Greed always leads to success" to WrongAnswerData(
                    imageResId = R.drawable.try_again,
                    explanation = "Incorrect! That is not the moral of the storys.",
                    description = "Wrong answer unfortunately! Please go back and try again!"
                )
            )
        ),
        "malin_kundang" to mapOf(
            0 to mapOf(
                "He wanted to explore the world" to WrongAnswerData(
                    imageResId = R.drawable.try_again,
                    explanation = "Incorrect! That is not Malin Kundang's reason to leave.",
                    description = "Wrong answer unfortunately! Please go back and try again!"
                ),
                "He was forced to leave by the villagers" to WrongAnswerData(
                    imageResId = R.drawable.try_again,
                    explanation = "Incorrect! It was Malin Kundang's own will.",
                    description = "Wrong answer unfortunately! Please go back and try again!"
                ),
                "His mother told him to leave" to WrongAnswerData(
                    imageResId = R.drawable.try_again,
                    explanation = "Incorrect! His mother did not tell him to leave.",
                    description = "Wrong answer unfortunately! Please go back and try again!"
                )
            ),
            1 to mapOf(
                "Angry and disappointed" to WrongAnswerData(
                    imageResId = R.drawable.try_again,
                    explanation = "Incorrect! That is not how Malin Kundang's mother feel.",
                    description = "Wrong answer unfortunately! Please go back and try again!"
                ),
                "Nervous and scared" to WrongAnswerData(
                    imageResId = R.drawable.try_again,
                    explanation = "Incorrect! That is not how Malin Kundang's mother feel.",
                    description = "Wrong answer unfortunately! Please go back and try again!"
                ),
                "Indifferent and uninterested" to WrongAnswerData(
                    imageResId = R.drawable.try_again,
                    explanation = "Incorrect! That is not how Malin Kundang's mother feel.",
                    description = "Wrong answer unfortunately! Please go back and try again!"
                )
            ),
            2 to mapOf(
                "He truly forgot about her" to WrongAnswerData(
                    imageResId = R.drawable.try_again,
                    explanation = "Incorrect! That is not Malin Kundang's reason.",
                    description = "Wrong answer unfortunately! Please go back and try again!"
                ),
                "His wife told him to ignore her" to WrongAnswerData(
                    imageResId = R.drawable.try_again,
                    explanation = "Incorrect! His wife did not tell him to do that.",
                    description = "Wrong answer unfortunately! Please go back and try again!"
                ),
                "He was in a hurry and didn’t see her" to WrongAnswerData(
                    imageResId = R.drawable.try_again,
                    explanation = "Incorrect! Malin Kundang did met his mother.",
                    description = "Wrong answer unfortunately! Please go back and try again!"
                )
            ),
            3 to mapOf(
                "He became even richer" to WrongAnswerData(
                    imageResId = R.drawable.try_again,
                    explanation = "Incorrect! He did not become richer.",
                    description = "Wrong answer unfortunately! Please go back and try again!"
                ),
                "He apologized to his mother" to WrongAnswerData(
                    imageResId = R.drawable.try_again,
                    explanation = "Incorrect! He did not apologize to his mother.",
                    description = "Wrong answer unfortunately! Please go back and try again!"
                ),
                "He ran away to another village" to WrongAnswerData(
                    imageResId = R.drawable.try_again,
                    explanation = "Incorrect! He did not run to another village.",
                    description = "Wrong answer unfortunately! Please go back and try again!"
                )
            )
        ),
        "malin_kundang_easy" to mapOf(
            0 to mapOf(
                "He wanted to explore the world" to WrongAnswerData(
                    imageResId = R.drawable.try_again,
                    explanation = "Incorrect! That is not Malin Kundang's reason to leave.",
                    description = "Wrong answer unfortunately! Please go back and try again!"
                ),
                "He was forced to leave by the villagers" to WrongAnswerData(
                    imageResId = R.drawable.try_again,
                    explanation = "Incorrect! It was Malin Kundang's own will.",
                    description = "Wrong answer unfortunately! Please go back and try again!"
                ),
                "His mother told him to leave" to WrongAnswerData(
                    imageResId = R.drawable.try_again,
                    explanation = "Incorrect! His mother did not tell him to leave.",
                    description = "Wrong answer unfortunately! Please go back and try again!"
                )
            ),
            1 to mapOf(
                "Angry and disappointed" to WrongAnswerData(
                    imageResId = R.drawable.try_again,
                    explanation = "Incorrect! That is not how Malin Kundang's mother feel.",
                    description = "Wrong answer unfortunately! Please go back and try again!"
                ),
                "Nervous and scared" to WrongAnswerData(
                    imageResId = R.drawable.try_again,
                    explanation = "Incorrect! That is not how Malin Kundang's mother feel.",
                    description = "Wrong answer unfortunately! Please go back and try again!"
                ),
                "Indifferent and uninterested" to WrongAnswerData(
                    imageResId = R.drawable.try_again,
                    explanation = "Incorrect! That is not how Malin Kundang's mother feel.",
                    description = "Wrong answer unfortunately! Please go back and try again!"
                )
            ),
            2 to mapOf(
                "He truly forgot about her" to WrongAnswerData(
                    imageResId = R.drawable.try_again,
                    explanation = "Incorrect! That is not Malin Kundang's reason.",
                    description = "Wrong answer unfortunately! Please go back and try again!"
                ),
                "His wife told him to ignore her" to WrongAnswerData(
                    imageResId = R.drawable.try_again,
                    explanation = "Incorrect! His wife did not tell him to do that.",
                    description = "Wrong answer unfortunately! Please go back and try again!"
                ),
                "He was in a hurry and didn’t see her" to WrongAnswerData(
                    imageResId = R.drawable.try_again,
                    explanation = "Incorrect! Malin Kundang did met his mother.",
                    description = "Wrong answer unfortunately! Please go back and try again!"
                )
            ),
            3 to mapOf(
                "He became even richer" to WrongAnswerData(
                    imageResId = R.drawable.try_again,
                    explanation = "Incorrect! He did not become richer.",
                    description = "Wrong answer unfortunately! Please go back and try again!"
                ),
                "He apologized to his mother" to WrongAnswerData(
                    imageResId = R.drawable.try_again,
                    explanation = "Incorrect! He did not apologize to his mother.",
                    description = "Wrong answer unfortunately! Please go back and try again!"
                ),
                "He ran away to another village" to WrongAnswerData(
                    imageResId = R.drawable.try_again,
                    explanation = "Incorrect! He did not run to another village.",
                    description = "Wrong answer unfortunately! Please go back and try again!"
                )
            )
        ),
        "bawang_merah_easy" to mapOf(
            0 to mapOf(
                "Right" to WrongAnswerData(
                    imageResId = R.drawable.try_again,
                    explanation = "Incorrect! That is not the kind-hearted one!",
                    description = "Wrong answer unfortunately! Please go back and try again!"
                )
            ),
            1 to mapOf(
                "Right" to WrongAnswerData(
                    imageResId = R.drawable.try_again,
                    explanation = "Incorrect! That is not who she found while searching for the red shawl.",
                    description = "Wrong answer unfortunately! Please go back and try again!"
                )
            ),
            2 to mapOf(
                "Left" to WrongAnswerData(
                    imageResId = R.drawable.try_again,
                    explanation = "Incorrect! That is not the reward she chose!",
                    description = "Wrong answer unfortunately! Please go back and try again!"
                )
            ),
            3 to mapOf(
                "Right" to WrongAnswerData(
                    imageResId = R.drawable.try_again,
                    explanation = "Incorrect! That did not come out of the reward she opened.",
                    description = "Wrong answer unfortunately! Please go back and try again!"
                )
            )
        )

    )

    fun getWrongAnswer(storyId: String, questionIndex: Int, selectedAnswer: String): WrongAnswerData? {
        val possibleAnswers = wrongAnswers[storyId]?.get(questionIndex)

        Log.d("WrongAnswerRepo", "Story ID: $storyId, Question Index: $questionIndex, Selected Answer: '$selectedAnswer'")

        possibleAnswers?.forEach { (key, value) ->
            Log.d("WrongAnswerRepo", "Checking: '$key' vs '${selectedAnswer.trim().lowercase()}'")
        }

        return possibleAnswers?.entries?.find { it.key.trim().lowercase() == selectedAnswer.trim().lowercase() }?.value
    }

}