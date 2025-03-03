package com.example.interactivestorytellingapp

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.mykotlinapp.R
import android.widget.Button

class storyFragment : Fragment() {
    private var paragraphs: List<String>? = null
    private lateinit var paragraphViews: MutableList<TextView>
    private lateinit var storyImageView: ImageView
    private var onReadAloudClick: ((String) -> Unit)? = null  // Callback for TTS

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        paragraphs = arguments?.getStringArrayList(ARG_PARAGRAPHS)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_story, container, false)

        // ✅ Get references
        storyImageView = view.findViewById(R.id.storyImageView)
        val imageResId = arguments?.getInt("imageResId", 0)
        val showButton = arguments?.getBoolean("showButton", true) ?: true
        val paragraphContainer: LinearLayout = view.findViewById(R.id.paragraphContainer)

        paragraphViews = mutableListOf()

        // ✅ Ensure ImageView behaves correctly
        if (imageResId != null && imageResId != 0) {
            storyImageView.setImageResource(imageResId)
        } else {
            storyImageView.visibility = View.GONE
        }

        // ✅ Dynamically add TextViews for each paragraph
        paragraphs?.forEachIndexed { index, text ->
            val textView = TextView(context).apply {
                this.text = text
                this.textSize = 18f
                this.setTextColor(Color.BLACK)
                this.setPadding(16, 8, 16, 8)
            }
            paragraphContainer.addView(textView)
            paragraphViews.add(textView)  // Store reference for future updates
        }

        // ✅ Read Aloud Button Handling
        val readAloudButton: Button = view.findViewById(R.id.btnReadAloud)
        readAloudButton.visibility = if (showButton) View.VISIBLE else View.GONE
        readAloudButton.setOnClickListener {
            paragraphs?.joinToString(" ")?.let { text -> onReadAloudClick?.invoke(text) }
        }

        return view
    }

    companion object {
        private const val ARG_PARAGRAPHS = "paragraphs"

        fun newInstance(
            imageResId: Int,
            paragraphs: List<String>,
            showButton: Boolean,
            onReadAloudClick: (String) -> Unit  // ✅ Ensure callback is passed
        ) = storyFragment().apply {
            arguments = Bundle().apply {
                putInt("imageResId", imageResId)
                putStringArrayList(ARG_PARAGRAPHS, ArrayList(paragraphs))
                putBoolean("showButton", showButton)
            }
            this.onReadAloudClick = onReadAloudClick  // ✅ Assign callback
        }
    }

    fun markParagraphAsRead(paragraphIndex: Int) {
        activity?.runOnUiThread {
            if (paragraphIndex in paragraphViews.indices) {
                val paragraphText = paragraphViews[paragraphIndex].text.toString()
                paragraphViews[paragraphIndex].text = "$paragraphText ✅" // Add checkmark
                paragraphViews[paragraphIndex].setTextColor(Color.GREEN) // Optional: Change color
            } else {
                Log.e("StoryFragment", "Invalid paragraph index: $paragraphIndex")
            }
        }
    }

    fun restoreParagraphProgress(progress: List<Boolean>) {
        activity?.runOnUiThread {
            if (::paragraphViews.isInitialized) {
                progress.forEachIndexed { index, isRead ->
                    if (isRead) {
                        val paragraphText = paragraphViews[index].text.toString()
                        paragraphViews[index].text = "$paragraphText ✅"
                        paragraphViews[index].setTextColor(Color.GREEN)
                    }
                }
            }
        }
    }

}
