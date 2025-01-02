package com.example.mykotlinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import androidx.viewpager2.widget.ViewPager2
import com.example.interactivestorytellingapp.StoryPage
import com.example.interactivestorytellingapp.StoryPagerAdapter

class Shepherd_Story : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager: ViewPager2 = findViewById(R.id.viewPager)

        val storySegments = listOf(
            StoryPage(R.drawable.rabbit_met, "Test."),
            StoryPage(R.drawable.rabbit_race, "Test2."),
            StoryPage(R.drawable.rabbit_sleep, "Test 3."),
            StoryPage(R.drawable.rabbit_lost, "Test 4.")
        )

        val adapter = StoryPagerAdapter(this, storySegments)
        viewPager.adapter = adapter

        // Add a listener to detect when the user reaches the last page
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                // check last page dari sini
                if (position == storySegments.size - 1) {
                    // fitur dari gpt untuk delay tiap kali sudah selesai quiz yg dijalani User
                    viewPager.postDelayed({
                        // untuk ambil data storynya akan ambil dari query
                        val intent = Intent(this@Shepherd_Story, Quiz::class.java)
                        intent.putExtra("story_id", "shepherd_story")  //indicate id story
                        startActivity(intent)
                        finish() // Close the story activity
                    }, 1000) // 1-second delay for better UX
                }
            }
        })
    }
}

