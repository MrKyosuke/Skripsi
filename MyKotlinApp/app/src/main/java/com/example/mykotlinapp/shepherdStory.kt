package com.example.mykotlinapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.interactivestorytellingapp.storyFragment
import com.example.interactivestorytellingapp.storyPage
import com.example.interactivestorytellingapp.storyPagerAdapter
import android.speech.tts.TextToSpeech
import java.util.Locale


class shepherdStory : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var buttonMic: Button
    private lateinit var buttonStartQuiz: Button
    private lateinit var storySegments: List<storyPage>
    private lateinit var speechRecognizer: SpeechRecognizer
    private lateinit var speechIntent: Intent
    private lateinit var paragraphProgress: MutableList<MutableList<Boolean>>
    private lateinit var textToSpeech: TextToSpeech

    companion object {
        private const val RECORD_AUDIO_REQUEST_CODE = 200
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonStartQuiz = findViewById(R.id.button_start_quiz)
        buttonStartQuiz.visibility = View.GONE

        // ✅ Initialize storySegments first
        storySegments = listOf(
            storyPage(R.drawable.village_scene, listOf(
                "A long time ago, in a quiet village, there lived a merchant and his daughter, Bawang Putih.",
                "The merchant loved her because she was kind-hearted",
                "One day, the merchant remarried, bringing home a new wife and her daughter, Bawang Merah.",
                "After the merchant left for work, Bawang Putih was forced to do all the chores by her stepmother and stepsister.")
            ),
            storyPage(R.drawable.incident, listOf(
                "As time passed, White Onion father fell ill and passed away.",
                "After his death, her stepmother and Bawang Merah treated Bawang Putih even worse, making her do all the household work.",
                " She was often punished if she made mistakes, and her life became very hard.")
            ),
            storyPage(R.drawable.lost_shawl, listOf(
                "One day, while washing clothes by the river, Bawang Putih accidentally let her stepmother’s favorite red shawl drift away.",
                "She was scared of returning home without it and went in search of the shawl.",
                "Along the way, she met a kind old grandmother who had found the shawl.")
            ),
            storyPage(R.drawable.pumpkin_gift, listOf(
                "The grandmother agreed to return the shawl but asked Bawang Putih to help with her chores first.",
                "After helping, the grandmother offered her a reward either a large or small pumpkin.",
                "Bawang Putih chose the small pumpkin and, following the grandmother's advice, waited until she got home to open it.")
            ),
            storyPage(R.drawable.treasure, listOf(
                "At home, Bawang Putih’s stepmother and Bawang Merah were furious with her.",
                "But when Bawang Putih cut open the small pumpkin, they were shocked to find it filled with sparkling jewels.",
                "The stepmother and Bawang Merah were jealous and wanted to get more jewels for themselves.")
            ),
            storyPage(R.drawable.false_pumpkin, listOf(
                "The next day, Bawang Merah and her mother went to the river, hoping to repeat Bawang Putih’s good fortune.",
                "They followed the same steps, helping the grandmother and choosing the biggest pumpkin.",
                "However, on their way home, they greedily opened the pumpkin too early.")
            ),
            storyPage(R.drawable.punishment, listOf(
                "To their horror, the pumpkin was filled with venomous creatures like snakes and scorpions.",
                "Both Bawang Merah and her mother died because of their greed, while Bawang Putih lived happily, continuing her father’s business and enjoying her wealth.")
            )
        )

        // ✅ Now it is safe to access storySegments
        storySegments.forEachIndexed { index, page ->
            page.paragraphs.forEach { paragraph ->
                println("Page $index: $paragraph")
            }
        }

        viewPager = findViewById(R.id.viewPager)
        buttonMic = findViewById(R.id.button_mic)
        buttonStartQuiz = findViewById(R.id.button_start_quiz)

        // ✅ Initialize paragraph progress tracking
        paragraphProgress = storySegments.map { page -> MutableList(page.paragraphs.size) { false } }.toMutableList()

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

        //untuk progress user tidak hilang kalaupun ke next page
        val adapter = storyPagerAdapter(
            this,
            storySegments,
            showReadAloudButton = true,
            onReadAloudClick = { text -> speakText(text) },
            getParagraphProgress = { pageIndex -> paragraphProgress[pageIndex] } // Pass stored progress
        )
        viewPager.adapter = adapter

        buttonStartQuiz.visibility = View.GONE
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == storySegments.size - 1) {
                    checkAllParagraphsCompletion()
                }
            }
        })

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), RECORD_AUDIO_REQUEST_CODE)
        } else {
            initializeSpeechRecognizer()
        }

        buttonMic.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
                startListening()
            } else {
                Toast.makeText(this, "Please enable microphone permission", Toast.LENGTH_SHORT).show()
            }
        }

        buttonStartQuiz.setOnClickListener {
            val intent = Intent(this@shepherdStory, Quiz::class.java)
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
                Toast.makeText(this@shepherdStory, "Listening...", Toast.LENGTH_SHORT).show()
            }

            override fun onBeginningOfSpeech() {}

            override fun onRmsChanged(rmsdB: Float) {}

            override fun onBufferReceived(buffer: ByteArray?) {}

            override fun onEndOfSpeech() {
                Toast.makeText(this@shepherdStory, "Processing...", Toast.LENGTH_SHORT).show()
            }

            override fun onError(error: Int) {
                val errorMessage = getErrorText(error)
                Toast.makeText(this@shepherdStory, "Error: $errorMessage", Toast.LENGTH_SHORT).show()
            }

            override fun onResults(results: Bundle?) {
                val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                val spokenText = matches?.get(0)?.trim() ?: return
                val currentPage = viewPager.currentItem
                val paragraphs = storySegments[currentPage].paragraphs

                for (i in paragraphs.indices) {
                    if (!paragraphProgress[currentPage][i] && isSpeechMatching(spokenText, paragraphs[i])) {
                        paragraphProgress[currentPage][i] = true
                        Log.d("SpeechRecognition", "Paragraph $i on page $currentPage marked as read: ${paragraphProgress[currentPage]}")
                        showCheckmark(currentPage, i)
                        checkPageCompletion()
                        checkAllParagraphsCompletion()
                        return
                    }
                }
                Toast.makeText(this@shepherdStory, "Try again! Read it clearly.", Toast.LENGTH_SHORT).show()
            }


            override fun onPartialResults(partialResults: Bundle?) {}

            override fun onEvent(eventType: Int, params: Bundle?) {}
        })
    }

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
        return matchCount >= expectedWords.size * 0.4
    }

    private fun moveToNextPage() {
        val nextPage = viewPager.currentItem + 1

        // ✅ Debugging Log
        Log.d("StoryNavigation", "Moving from page ${viewPager.currentItem} to page $nextPage")

        if (nextPage < storySegments.size) {
            viewPager.setCurrentItem(nextPage, true)
        } else {
            Log.d("StoryNavigation", "Reached last page, transitioning to quiz")
            val intent = Intent(this, Quiz::class.java)
            intent.putExtra("story_id", "bawang_merah")
            startActivity(intent)
            finish()
        }
    }

    //untuk liat errornya dimana aja, jadi bisa di-display error messagenya
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

    private fun checkPageCompletion() {
        val currentPage = viewPager.currentItem

        // ✅ Debugging Log
        Log.d("PageCompletion", "Checking page $currentPage - Progress: ${paragraphProgress[currentPage]}")

        if (paragraphProgress[currentPage].all { it }) {
            Log.d("PageCompletion", "All paragraphs completed on page $currentPage")
            moveToNextPage()
        }
        checkAllParagraphsCompletion()
    }

    private fun checkAllParagraphsCompletion() {
        val allRead = paragraphProgress.all { pageProgress -> pageProgress.all { it } } // ✅ Check all pages

        if (allRead) {
            Log.d("StoryProgress", "All paragraphs in the story have been read.")
            buttonStartQuiz.visibility = View.VISIBLE // ✅ Show Quiz button
        } else {
            buttonStartQuiz.visibility = View.GONE // ✅ Hide Quiz button until all are read
        }
    }

    private fun showCheckmark(pageIndex: Int, paragraphIndex: Int) {
        val adapter = viewPager.adapter as? storyPagerAdapter
        val fragment = supportFragmentManager.findFragmentByTag("f$pageIndex") as? storyFragment

        if (fragment != null) {
            fragment.markParagraphAsRead(paragraphIndex)
            Log.d("Checkmark", "Marked paragraph $paragraphIndex on page $pageIndex")
        } else {
            Log.e("Checkmark", "Could not find fragment for page $pageIndex")
        }
    }


    private fun speakText(text: String) {
        if (::textToSpeech.isInitialized) {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        speechRecognizer.destroy()
        if (::textToSpeech.isInitialized) {
            textToSpeech.stop()
            textToSpeech.shutdown()
        }
    }
}