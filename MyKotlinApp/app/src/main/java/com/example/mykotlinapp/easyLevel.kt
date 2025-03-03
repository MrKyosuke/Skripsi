package com.example.mykotlinapp

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent

class easyLevel : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_easy_level)

        val backButton = findViewById<ImageButton>(R.id.button_back)
        backButton.setOnClickListener {
            finish()
        }

        val buttonbawangMerahEasy = findViewById<ImageButton>(R.id.button_bawang)
        buttonbawangMerahEasy.setOnClickListener {
            val intent = Intent(this, bawangMerahEasy::class.java)
            startActivity(intent)
        }

        val buttonmalinKundangEasy = findViewById<ImageButton>(R.id.button_malin)
        buttonmalinKundangEasy.setOnClickListener {
            val intent = Intent(this, malinKundangEasy::class.java)
            startActivity(intent)
        }
    }
}
