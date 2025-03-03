package com.example.interactivestorytellingapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class storyPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val stories: List<storyPage>,
    private val onReadAloudClick: (String) -> Unit,
    private val getParagraphProgress: (Int) -> List<Boolean>,
    private val showReadAloudButton: Boolean) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = stories.size

    override fun createFragment(position: Int): Fragment {
        val story = stories[position]
        return storyFragment.newInstance(
            story.imageResId,
            story.paragraphs,
            showReadAloudButton,
            onReadAloudClick
        ).apply {
            restoreParagraphProgress(getParagraphProgress(position))
        } // Pass as List<String>
    }


}
