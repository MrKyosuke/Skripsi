package com.example.mykotlinapp

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.interactivestorytellingapp.storyPage
import com.example.interactivestorytellingapp.storyPagerAdapterEasy
import com.example.mykotlinapp.easyQuiz
import quizRepository
import java.util.Locale

class bawangMerahEasy : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: storyPagerAdapterEasy
    private lateinit var buttonStartQuiz: Button
    private lateinit var storySegments: List<storyPage>
    private lateinit var textToSpeech: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_easy)

        buttonStartQuiz = findViewById(R.id.button_start_quiz)
        viewPager = findViewById(R.id.viewPager)

        // ✅ Hide button initially
        buttonStartQuiz.visibility = View.GONE

        // ✅ Initialize storySegments
        storySegments = listOf(
            storyPage(R.drawable.village_scene, listOf(
                "A long time ago, in a small village, lived a kind girl named Bawang Putih.",
                "She had a loving father.",
                "One day, her father married a woman who had a daughter, Bawang Merah.",
                "After that, Bawang Putih had to do all the housework.")
            ),
            storyPage(R.drawable.incident, listOf(
                "Bawang Putih’s father got very sick and passed away.",
                "Her stepmother and Bawang Merah were mean to her.",
                "They made her work all day and punished her for small mistakes.")
            ),
            storyPage(R.drawable.lost_shawl, listOf(
                "One day, while washing clothes, Bawang Putih lost her stepmother’s red shawl in the river.",
                "She was scared and searched for it.",
                "She met a kind old woman who had the shawl.")
            ),
            storyPage(R.drawable.pumpkin_gift, listOf(
                "The old woman asked Bawang Putih to help her with chores before giving back the shawl.",
                "After helping, Bawang Putih got to choose a small or big pumpkin.",
                "She picked the small one.")
            ),
            storyPage(R.drawable.treasure, listOf(
                "At home, her stepmother was angry.",
                "But when Bawang Putih opened the pumpkin, it was full of shiny jewels!",
                "Her stepmother and Bawang Merah became jealous.")
            ),
            storyPage(R.drawable.false_pumpkin, listOf(
                "The next day, Bawang Merah tried the same thing",
                "She helped the old woman and took the biggest pumpkin.",
                "But she and her mother opened it too soon.")
            ),
            storyPage(R.drawable.punishment, listOf(
                "The pumpkin had snakes and scorpions! They ran away in fear.",
                "Bawang Putih lived happily with her father’s business and her new treasures.")
            )
        )

        adapter = storyPagerAdapterEasy(
            this,
            storySegments,
            showReadAloudButton = true,
            onReadAloudClick = { text -> speakText(text) }
        )
        viewPager.adapter = adapter

        // ✅ Show quiz button only on the last page
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                buttonStartQuiz.visibility = if (position == storySegments.size - 1) View.VISIBLE else View.GONE
            }
        })

        // ✅ Handle quiz button click (Replace `QuizActivity::class.java` with your actual quiz activity)
        buttonStartQuiz.setOnClickListener {
            val intent = Intent(this@bawangMerahEasy, easyQuiz::class.java)
            intent.putExtra("story_id", "bawang_merah_easy") // Pass the story ID
            startActivity(intent)

        }

        // ✅ Initialize Text-to-Speech
        textToSpeech = TextToSpeech(this) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val result = textToSpeech.setLanguage(Locale.ENGLISH)
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("TTS", "Language not supported or missing data")
                } else {
                    Log.d("TTS", "Text-to-Speech is initialized successfully")
                }
            } else {
                Log.e("TTS", "Text-to-Speech initialization failed")
            }
        }
    }

    private fun speakText(text: String) {
        if (::textToSpeech.isInitialized) {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::textToSpeech.isInitialized) {
            textToSpeech.stop()
            textToSpeech.shutdown()
        }
    }
}
