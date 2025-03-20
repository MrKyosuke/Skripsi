package com.example.mykotlinapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class Dashboard : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)

        // Initialize CardViews
        val dashboardCard: CardView = findViewById(R.id.cardView1)
        val aboutCard: CardView = findViewById(R.id.cardView2)


        // Set onClickListeners
        dashboardCard.setOnClickListener {
            val intent = Intent(this, Level::class.java)
            startActivity(intent)
        }

        aboutCard.setOnClickListener {
            val intent = Intent(this, About::class.java)
            startActivity(intent)
        }
    }

    override fun onClick(v: View?) {
        // Optional: implement logic if needed
    }
}
