package com.example.hackathon.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.hackathon.R
import com.example.hackathon.base.BaseFragment
import com.example.hackathon.databinding.OwnerDetailFragmentBinding
import com.example.hackathon.domain.response.DataState
import com.example.hackathon.view.adapter.ViewPagerAdapter
import com.example.hackathon.viewmodel.OwnerDetailViewModel
import com.google.android.material.tabs.TabLayoutMediator

class OwnerDetailFragment :
    BaseFragment<OwnerDetailFragmentBinding>(R.layout.owner_detail_fragment) {
    private val viewModel: OwnerDetailViewModel by viewModels()
    private val tabNames = listOf<String>("메뉴", "리뷰")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    fun init() {
        getStoreInfo()
    }

    fun bind() = with(binding) {
        viewPagerOwnerDetail.apply {
            adapter = ViewPagerAdapter(requireActivity())
        }
        TabLayoutMediator(tabOwnerDetail, viewPagerOwnerDetail) { tab, position ->
            tab.text = tabNames[position]
        }.attach()
        btnBackOwnerDetail.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    fun observe() = with(viewModel) {
        storeState.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Success -> {
                    binding.store = it.data
                }
                is DataState.Failure -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                is DataState.Loading -> {
                    //shimmer
                }
            }
        }
    }

    private fun getStoreInfo() {
        val storeId = arguments?.getLong("storeId")!!
        viewModel.getStoreInfo(storeId)
    }
}