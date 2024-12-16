package com.example.mykotlinapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class Level : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.level_activity)

        // tiap level yang tersedia
        findViewById<CardView>(R.id.card_easy).setOnClickListener {
            val intent = Intent(this, EasyLevel::class.java)
            startActivity(intent)
        }
        findViewById<CardView>(R.id.card_medium).setOnClickListener {
            val intent = Intent(this, MediumLevel::class.java)
            startActivity(intent)
        }
        findViewById<CardView>(R.id.card_hard).setOnClickListener {
            val intent = Intent(this, HardLevel::class.java)
            startActivity(intent)
        }
    }
}
