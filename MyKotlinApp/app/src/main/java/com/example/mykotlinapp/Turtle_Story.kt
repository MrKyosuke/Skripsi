package com.example.mykotlinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import androidx.viewpager2.widget.ViewPager2
import com.example.interactivestorytellingapp.StoryPage
import com.example.interactivestorytellingapp.StoryPagerAdapter

class Turtle_Story : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager: ViewPager2 = findViewById(R.id.viewPager)

        val storySegments = listOf(
            StoryPage(R.drawable.rabbit_met, listOf(
                "One day, Rabbit asked the turtle to run a race because he felt he would win the race."
            )),
            StoryPage(R.drawable.rabbit_race, listOf(
                "On race day, the Rabbit raced fast at the start. Meanwhile, the tortoise tried to run as best he could to catch the hare."
            )),
            StoryPage(R.drawable.rabbit_sleep, listOf(
                "Approaching the finish line, the arrogant Rabbit chose to sleep for a while under a tree because he was sure that the Tortoise couldn't possibly catch up with him."
            )),
            StoryPage(R.drawable.rabbit_lost, listOf(
                "However, it turned out that he fell asleep longer than planned. In the end, the turtle managed to get ahead of him and won the race."
            ))
        )

        val adapter = StoryPagerAdapter(this, storySegments)
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
}

