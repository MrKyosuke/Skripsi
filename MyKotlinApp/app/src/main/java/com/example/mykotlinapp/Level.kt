package com.example.mykotlinapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class Level : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.level_activity)

        // Easy Level
//        findViewById<CardView>(R.id.card_easy).setOnClickListener {
//            val intent = Intent(this, easyLevel::class.java)
//            startActivity(intent)
//        }

        // Medium Level
        findViewById<CardView>(R.id.card_medium).setOnClickListener {
            val intent = Intent(this, mediumLevel::class.java)
            startActivity(intent)
        }

        // Hard Level - Show "Coming Soon!" popup
        findViewById<CardView>(R.id.card_hard).setOnClickListener {
            showComingSoonDialog()
        }
    }

    private fun showComingSoonDialog() {
        // Create an AlertDialog to display the "Coming Soon!" message
        AlertDialog.Builder(this)
            .setTitle("Coming Soon!")
            .setMessage("The Hard Level is not available yet. Stay tuned!")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss() // Close the dialog when the user clicks "OK"
            }
            .show()
    }
}
