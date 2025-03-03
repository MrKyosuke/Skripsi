package com.example.interactivestorytellingapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


class StoryPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val stories: List<StoryPage>,
    private val onReadAloudClick: (String) -> Unit,
    private val showReadAloudButton: Boolean // Pass this flag
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = stories.size

    override fun createFragment(position: Int): Fragment {
        val story = stories[position]

        return StoryFragment.newInstance(
            story.imageResId,
            story.storyText,
            showReadAloudButton,
            onReadAloudClick)
    }
}
