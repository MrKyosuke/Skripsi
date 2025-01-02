package com.example.interactivestorytellingapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.mykotlinapp.R

class StoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_story, container, false)

        val imageView: ImageView = view.findViewById(R.id.storyImage)
        val textView: TextView = view.findViewById(R.id.storyText)

        val args = arguments
        args?.let {
            val imageResId = it.getInt("imageResId")
            val storyText = it.getString("storyText")

            imageView.setImageResource(imageResId)
            textView.text = storyText
        }

        return view
    }

    companion object {
        fun newInstance(imageResId: Int, storyText: String) = StoryFragment().apply {
            arguments = Bundle().apply {
                putInt("imageResId", imageResId)
                putString("storyText", storyText)
            }
        }
    }
}
