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
                "One day, her father married a woman who had a daughter, Bawang Merah. ",
                "After that, Bawang Putih had to do all the housework.")
            ),
            storyPage(R.drawable.incident, listOf(
                "Bawang Putih’s father got very sick and passed away.",
                "Her stepmother and Bawang Merah were mean to her",
                "They made her work all day and punished her for small mistakes")
            ),
            storyPage(R.drawable.lost_shawl, listOf(
                "One day, while washing clothes, Bawang Putih lost her stepmother’s red shawl in the river",
                "She was scared and searched for it",
                "She met a kind old woman who had the shawl.")
            ),
            storyPage(R.drawable.pumpkin_gift, listOf(
                "The grandmother agreed to return the shawl but asked Bawang Putih to help with her chores first.",
                "After helping, the grandmother offered her a reward either a large or small pumpkin.",
                "Bawang Putih chose the small pumpkin and, following the grandmother's advice, waited until she got home to open it.")
            ),
            storyPage(R.drawable.treasure, listOf(
                "At home, Bawang Putih’s stepmother and Bawang Merah were furious with her.",
                "But when Bawang Putih cut open the small pumpkin, they were shocked to find it filled with sparkling jewels.",
                "The stepmother and Bawang Merah were jealous and wanted to get more jewels for themselves.")
            ),
            storyPage(R.drawable.false_pumpkin, listOf(
                "The next day, Bawang Merah and her mother went to the river, hoping to repeat Bawang Putih’s good fortune.",
                "They followed the same steps, helping the grandmother and choosing the biggest pumpkin.",
                "However, on their way home, they greedily opened the pumpkin too early.")
            ),
            storyPage(R.drawable.punishment, listOf(
                "To their horror, the pumpkin was filled with venomous creatures like snakes and scorpions.",
                "Both Bawang Merah and her mother died because of their greed, while Bawang Putih lived happily, continuing her father’s business and enjoying her wealth.")
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
