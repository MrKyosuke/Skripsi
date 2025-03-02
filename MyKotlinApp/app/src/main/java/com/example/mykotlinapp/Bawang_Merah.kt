package com.example.mykotlinapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.interactivestorytellingapp.StoryPage
import com.example.interactivestorytellingapp.StoryPagerAdapter
import java.util.*

class Bawang_Merah : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var buttonMic: Button
    private lateinit var buttonStartQuiz: Button
    private lateinit var storySegments: List<StoryPage>
    private lateinit var speechRecognizer: SpeechRecognizer
    private lateinit var speechIntent: Intent

    companion object {
        private const val RECORD_AUDIO_REQUEST_CODE = 200
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager)
        buttonMic = findViewById(R.id.button_mic)
        buttonStartQuiz = findViewById(R.id.button_start_quiz)

        storySegments = listOf(
            StoryPage(R.drawable.village_scene, "A long time ago, in a quiet village, there lived a merchant and his daughter, Bawang Putih..."),
            StoryPage(R.drawable.incident, "As time passed, Bawang Putih's father fell ill and passed away..."),
            StoryPage(R.drawable.lost_shawl, "One day, while washing clothes by the river, Bawang Putih accidentally let her stepmother’s favorite red shawl drift away..."),
            StoryPage(R.drawable.pumpkin_gift, "The grandmother agreed to return the shawl but asked Bawang Putih to help with her chores first..."),
            StoryPage(R.drawable.treasure, "At home, Bawang Putih’s stepmother and Bawang Merah were furious with her..."),
            StoryPage(R.drawable.false_pumpkin, "The next day, Bawang Merah and her mother went to the river, hoping to repeat Bawang Putih’s good fortune..."),
            StoryPage(R.drawable.punishment, "To their horror, the pumpkin was filled with venomous creatures like snakes and scorpions...")
        )

        val adapter = StoryPagerAdapter(this, storySegments)
        viewPager.adapter = adapter

        buttonStartQuiz.visibility = View.GONE

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                buttonStartQuiz.visibility = if (position == storySegments.size - 1) View.VISIBLE else View.GONE
            }
        })

        // Log untuk cek lagi request permission, untuk kira" bisa lihat errornya dimana ketika User ingin press Mic Button
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), RECORD_AUDIO_REQUEST_CODE)
        } else {
            initializeSpeechRecognizer()
        }

        // add fitur untuk dapat request mic permission ke android device, agar tidak nampilin pop Up google
        buttonMic.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
                startListening()
            } else {
                Toast.makeText(this, "Please enable microphone permission", Toast.LENGTH_SHORT).show()
            }
        }

        buttonStartQuiz.setOnClickListener {
            val intent = Intent(this@Bawang_Merah, Quiz::class.java)
            intent.putExtra("story_id", "bawang_merah")
            startActivity(intent)
            finish()
        }
    }

    private fun initializeSpeechRecognizer() {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)
        speechIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH)
            putExtra(RecognizerIntent.EXTRA_PROMPT, "Read the text !")
        }

        speechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(params: Bundle?) {
                Toast.makeText(this@Bawang_Merah, "Listening...", Toast.LENGTH_SHORT).show()
            }
            //pakai func ini untuk bisa cek paragraf panjang
            override fun onBeginningOfSpeech() {}

            override fun onRmsChanged(rmsdB: Float) {}

            override fun onBufferReceived(buffer: ByteArray?) {}

            override fun onEndOfSpeech() {
                Toast.makeText(this@Bawang_Merah, "Processing...", Toast.LENGTH_SHORT).show()
            }

            override fun onError(error: Int) {
                val errorMessage = getErrorText(error)
                Toast.makeText(this@Bawang_Merah, "Error: $errorMessage", Toast.LENGTH_SHORT).show()
            }

            //hasil text yg di input dan di expect system pakai func ini, dan akan otomatis ke next page kalau sudah benar
            override fun onResults(results: Bundle?) {
                val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                val spokenText = matches?.get(0)?.trim() ?: return
                val expectedText = storySegments[viewPager.currentItem].storyText

                if (isSpeechMatching(spokenText, expectedText)) {
                    moveToNextPage()
                } else {
                    Toast.makeText(this@Bawang_Merah, "Try again! Read it clearly.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onPartialResults(partialResults: Bundle?) {}

            override fun onEvent(eventType: Int, params: Bundle?) {}
        })
    }

    //tambah func ini error ini untuk test kalau permission dari mic nya belum di allow User
    private fun startListening() {
        if (!SpeechRecognizer.isRecognitionAvailable(this)) {
            Toast.makeText(this, "Speech Recognition is not available", Toast.LENGTH_LONG).show()
            return
        }
        speechRecognizer.startListening(speechIntent)
    }

    private fun isSpeechMatching(spokenText: String, expectedText: String): Boolean {
        val spokenWords = spokenText.lowercase(Locale.getDefault()).split(" ")
        val expectedWords = expectedText.lowercase(Locale.getDefault()).split(" ")

        val matchCount = spokenWords.count { it in expectedWords }
        return matchCount >= expectedWords.size * 0.7
    }

    private fun moveToNextPage() {
        val nextPage = viewPager.currentItem + 1
        if (nextPage < storySegments.size) {
            viewPager.setCurrentItem(nextPage, true)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        speechRecognizer.destroy()
    }

    //semua error code dari GPT untuk test apa saja errornya, tapi masih bisa dicek manual dari Logcat
    private fun getErrorText(errorCode: Int): String {
        return when (errorCode) {
            SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> "Network timeout"
            SpeechRecognizer.ERROR_NETWORK -> "Network error"
            SpeechRecognizer.ERROR_AUDIO -> "Audio recording error"
            SpeechRecognizer.ERROR_SERVER -> "Server error"
            SpeechRecognizer.ERROR_CLIENT -> "Client error"
            SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> "No speech input"
            SpeechRecognizer.ERROR_NO_MATCH -> "No matching speech"
            SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> "Recognizer is busy"
            SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> "Insufficient permissions"
            else -> "Unknown error"
        }
    }

    //func untuk nampillin request Access Mic ke User
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == RECORD_AUDIO_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initializeSpeechRecognizer()
                Toast.makeText(this, "Microphone permission granted!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Microphone permission denied! Please enable it in settings.", Toast.LENGTH_LONG).show()
            }
        }
    }
}
