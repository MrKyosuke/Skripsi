package com.example.mykotlinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import androidx.viewpager2.widget.ViewPager2
import com.example.interactivestorytellingapp.StoryPage
import com.example.interactivestorytellingapp.StoryPagerAdapter

class Elephant_Story : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager: ViewPager2 = findViewById(R.id.viewPager)

        val storySegments = listOf(
            StoryPage(R.drawable.elephant_sees_ant, "Once upon a time, a small ant and a large elephant lived in the forest. This huge elephant is very strong and is known to be easily angered by small things. He also often underestimated every other small animal that lived in the forest. " +
                    "\n\nSomewhere in the forest, there was a small ant who lived with his family in a deep tree hole. Ants are known to be kind and hardworking. Every day, these little ants and their families go looking for food. Often, they also meet elephants on their way while looking for food."),
            StoryPage(R.drawable.elephant_shoots_water, "Whenever this family of ants met elephants, the elephants would make fun of the ants and annoy them. The giant elephant was very proud of its strength and body. He always uses his power to make the ants angry. All members of the ant family were worried about the elephant's joke. " +
                    "\n\nOne fine morning, when the little ant and his family went to look for food, the elephant sprayed water from his trunk towards the ant family.This incident shocked the entire ant family and the little ant started crying. The ants asked him, “Hey! What's wrong with you? Why do you keep giving us trouble?” Seeing the ant crying, the elephant said angrily, “Stop crying or I will crush you to death.” The poor little ant stopped crying, but he decided to teach the giant elephant a lesson."),
            StoryPage(R.drawable.elephant_revenge, "Another ant said, “You must not fight with the elephant, he is very angry and very strong! He can destroy you.” " +
                    "\n\nHowever, the tough ant answered, \"Hmmm... Something needs to be done about it.\" " +
                    "\n\nThe next day when the ant was going to work, the little ant decided to teach the elephant a lesson. He quietly climbed onto the elephant's body and walked until he entered the elephant's trunk! As soon as he entered, he started biting the elephant. The elephant shouted, “Aaah! It hurts!\" The elephant tried everything but couldn't get the ants to stop biting him or."),
            StoryPage(R.drawable.elephant_apologize, "The elephant screamed again, “Aaah! Please stop! Stop now!” There was nothing he could do to get the ant out of his trunk, until finally the elephant got tired. The ant replied from inside his trunk, “Okay, I hope you now know how other living things feel when you hurt them.” “Yes, I know! Please stop now!” the elephant begged the ant. “Okay then,” the ant said as he stopped biting him and came out of his trunk. " +
                    "The elephant was finally relieved, and he also apologized to the ant, “I’m sorry! I now understand how it feels to be disturbed. I promise I will never do it again.” " +
                    "\n\n\nFrom that day on, the elephant promised not to disturb any living thing in the forest. And this is how the giant elephant learned. After that, all the living things in the forest lived happily ever after..")
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
                        val intent = Intent(this@Elephant_Story, Quiz::class.java)
                        intent.putExtra("story_id", "elephant_story")  //indicate id story
                        startActivity(intent)
                        finish() // Close the story activity
                    }, 10000) // 1-second delay for better UX
                }
            }
        })
    }
}

