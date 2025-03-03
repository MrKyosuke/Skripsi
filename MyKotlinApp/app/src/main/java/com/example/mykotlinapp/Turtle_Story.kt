package com.example.mykotlinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import androidx.viewpager2.widget.ViewPager2
import com.example.interactivestorytellingapp.StoryPage
import com.example.interactivestorytellingapp.StoryPagerAdapter
import android.speech.tts.TextToSpeech
import android.view.View
import java.util.Locale
import android.widget.Button

class Turtle_Story : AppCompatActivity() {

    private lateinit var textToSpeech: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager: ViewPager2 = findViewById(R.id.viewPager)


        val storySegments = listOf(
            StoryPage(R.drawable.rabbit_met, "One day, Rabbit asked the turtle to run a race because he felt he would win the race."),
            StoryPage(R.drawable.rabbit_race, "On race day, the Rabbit raced fast at the start. Meanwhile, the tortoise tried to run as best he could to catch the hare."),
            StoryPage(R.drawable.rabbit_sleep, "Approaching the finish line, the arrogant Rabbit chose to sleep for a while under a tree because he was sure that the Tortoise couldn't possibly catch up with him."),
            StoryPage(R.drawable.rabbit_lost, "However, it turned out that he fell asleep longer than planned. In the end, the turtle managed to get ahead of him and won the race.")
        )

        textToSpeech = TextToSpeech(this) { status ->
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech.language = Locale.ENGLISH
            }
        }

        val adapter = StoryPagerAdapter(
            this,
            storySegments,
            showReadAloudButton = true, // Hide button
            onReadAloudClick = { text -> speakText(text) }
        )
        viewPager.adapter = adapter

        // untuk detect listener
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                // check last page dari sini
                if (position == storySegments.size - 1) {
                    // fitur dari gpt untuk delay tiap kali sudah selesai quiz yg dijalani User
                    viewPager.postDelayed({
                        // untuk ambil data storynya akan ambil dari query
                        val intent = Intent(this@Turtle_Story, Quiz::class.java)
                        intent.putExtra("story_id", "turtle_story")  // Pass the story id to the quiz
                        startActivity(intent)
                        finish() // Close the story activity
                    }, 10000) // 1-second delay for better UX
                }
            }
        })
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

