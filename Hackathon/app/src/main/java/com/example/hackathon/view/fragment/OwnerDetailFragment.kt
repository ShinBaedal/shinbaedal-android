package com.example.hackathon.view.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hackathon.R
import com.example.hackathon.base.BaseFragment
import com.example.hackathon.databinding.OwnerDetailFragmentBinding
import com.example.hackathon.domain.entity.Menu
import com.example.hackathon.domain.response.DataState
import com.example.hackathon.view.adapter.RecyclerViewItemClickListener
import com.example.hackathon.view.adapter.ViewPagerAdapter
import com.example.hackathon.viewmodel.OwnerDetailViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class OwnerDetailFragment :
    BaseFragment<OwnerDetailFragmentBinding>(R.layout.owner_detail_fragment),
    RecyclerViewItemClickListener<Menu> {
    private val viewModel: OwnerDetailViewModel by viewModels()
    private val tabNames = listOf<String>("메뉴", "리뷰")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
        observe()
        getStoreInfo()
    }


    private fun bind() = with(binding) {
        viewPagerOwnerDetail.apply {
            adapter = ViewPagerAdapter(requireActivity(), this@OwnerDetailFragment)
        }
//        TabLayoutMediator(tabOwnerDetail, viewPagerOwnerDetail) { tab, position ->
//            tab.text = tabNames[position]
//        }.attach()
        btnBackOwnerDetail.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun observe() = with(viewModel) {
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

    override fun onclick(data: Menu) {
        binding.menu=data
        binding.constraintLayout4.transitionToEnd()
    }
}