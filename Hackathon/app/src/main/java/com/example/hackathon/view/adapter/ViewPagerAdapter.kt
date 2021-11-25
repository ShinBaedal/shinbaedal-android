package com.example.hackathon.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.hackathon.domain.entity.Menu
import com.example.hackathon.view.fragment.OwnerMenuFragment
import com.example.hackathon.view.fragment.OwnerReviewFragment

class ViewPagerAdapter(
    activity: FragmentActivity,
    private val onClick: RecyclerViewItemClickListener<Menu>
) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 100

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> OwnerMenuFragment(onClick)
            else -> OwnerReviewFragment()
        }


    }
}