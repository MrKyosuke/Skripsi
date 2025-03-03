package com.example.interactivestorytellingapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mykotlinapp.R
import android.widget.Button

class StoryFragment : Fragment() {

    private var onReadAloudClick: ((String) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_story, container, false)

        val imageView: ImageView = view.findViewById(R.id.storyImage)
        val textView: TextView = view.findViewById(R.id.storyText)
        val readAloudButton: Button = view.findViewById(R.id.btnReadAloud)

        val args = arguments
        args?.let {
            val imageResId = it.getInt("imageResId")
            val storyText = it.getString("storyText")
            val showButton = it.getBoolean("showButton", true) // Default to true

            imageView.setImageResource(imageResId)
            textView.text = storyText

            readAloudButton.visibility = if (showButton) View.VISIBLE else View.GONE

            readAloudButton.setOnClickListener {
                storyText?.let { text -> onReadAloudClick?.invoke(text) }
            }
        }

        return view
    }


    companion object {
        fun newInstance(
            imageResId: Int,
            storyText: String,
            showButton: Boolean,
            onReadAloudClick: (String) -> Unit
        ) = StoryFragment().apply {
            arguments = Bundle().apply {
                putInt("imageResId", imageResId)
                putString("storyText", storyText)
                putBoolean("showButton", showButton)
            }
            this.onReadAloudClick = onReadAloudClick
        }
    }
}
