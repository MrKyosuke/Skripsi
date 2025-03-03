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

class malinKundangEasy : AppCompatActivity() {
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
            storyPage(R.drawable.poor_fisherman, listOf(
                "A long time ago, Malin Kundang lived with his mother in a small village.",
                "They were poor, but his mother loved him very much.",)
            ),
            storyPage(R.drawable.malin_leave, listOf(
                "One day, a big ship came to the village.",
                "Malin saw a chance to be rich.",
                "He promised to return and left his mother behind.")
            ),
            storyPage(R.drawable.success_merc, listOf(
                "Malin worked hard and became very rich.",
                "He married a noblewoman and forgot about his mother.",)
            ),
            storyPage(R.drawable.happy_mom, listOf(
                "One day, his ship came back.",
                "His mother was so happy! She ran to the shore, calling his name.",)
            ),
            storyPage(R.drawable.rejection, listOf(
                "But Malin was ashamed.",
                "He told his wife he didn’t know his mother.",
                "His mother cried and begged, but Malin turned away.")
            ),
            storyPage(R.drawable.curse, listOf(
                "His mother was heartbroken.",
                "She prayed to the sky, asking for punishment for Malin’s cruelty.",)
            ),
            storyPage(R.drawable.the_punishment, listOf(
                "A big storm came.",
                "The sea was angry.",
                "Malin’s ship was struck by lightning, and he turned into stone.")
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
            val intent = Intent(this@malinKundangEasy, easyQuiz::class.java)
            intent.putExtra("story_id", "malin_kundang_easy") // Pass the story ID
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
