package com.example.interactivestorytellingapp

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.mykotlinapp.R

class StoryFragment : Fragment() {
    private var paragraphs: List<String>? = null
    private lateinit var paragraphViews: List<TextView>
    private lateinit var storyImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        paragraphs = arguments?.getStringArrayList(ARG_PARAGRAPHS) // Retrieve list properly
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_story, container, false)

        // ✅ Initialize UI Elements
        storyImageView = view.findViewById(R.id.storyImageView)
        val imageResId = arguments?.getInt("imageResId", 0) // "no image set"

        if (imageResId != 0) {
            if (imageResId != null) {
                storyImageView.setImageResource(imageResId)
            }
        } else {
            storyImageView.visibility = View.GONE  // Hide ImageView if no image is provided
        }

        // ✅ Initialize TextViews for paragraphs
        val paragraphContainer = view.findViewById<ViewGroup>(R.id.paragraphContainer) // Ensure this exists in XML
        paragraphViews = paragraphs?.map { paragraphText ->
            val textView = TextView(requireContext()).apply {
                text = paragraphText
                textSize = 16f
                setPadding(8, 8, 8, 8)
                setTextColor(Color.BLACK)
            }
            paragraphContainer.addView(textView) // Add each TextView to the container
            textView
        } ?: emptyList()

        return view // ✅ Return the inflated view
    }

    fun markParagraphAsRead(paragraphIndex: Int) {
        if (::paragraphViews.isInitialized && paragraphIndex in paragraphViews.indices) {
            val paragraphView = paragraphViews[paragraphIndex]
            paragraphView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.checkmark, 0)
            paragraphViews[paragraphIndex].setTextColor(Color.GRAY)
        }
    }

    companion object {
        private const val ARG_PARAGRAPHS = "paragraphs"

        fun newInstance(imageResId: Int, paragraphs: List<String>): StoryFragment {
            return StoryFragment().apply {
                arguments = Bundle().apply {
                    putInt("imageResId", imageResId)
                    putStringArrayList(ARG_PARAGRAPHS, ArrayList(paragraphs)) // Convert to ArrayList
                }
            }
        }
    }
}
