package com.example.hackathon.view.fragment

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hackathon.R
import com.example.hackathon.base.BaseFragment
import com.example.hackathon.databinding.OwnerReviewFragmentBinding
import com.example.hackathon.domain.entity.Review
import com.example.hackathon.domain.entity.Store
import com.example.hackathon.view.adapter.RecyclerViewItemClickListener
import com.example.hackathon.view.adapter.ReviewAdapter
import com.example.hackathon.viewmodel.OwnerReviewViewModel
import com.google.android.material.chip.Chip
import kotlinx.coroutines.launch
import java.util.*
import android.os.Bundle
import com.example.hackathon.view.dialog.ReviewBottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class OwnerReviewFragment :
    BaseFragment<OwnerReviewFragmentBinding>(R.layout.owner_review_fragment),
    RecyclerViewItemClickListener<Review> {

    private val viewModel: OwnerReviewViewModel by viewModels()
    private val reviewAdapter: ReviewAdapter by lazy {
        ReviewAdapter(this)
    }

    override fun OwnerReviewFragmentBinding.onCreateView() {

    }

    override fun OwnerReviewFragmentBinding.onViewCreated() {
        setAdapter()
        chipClickType()
        observeReviewData()
    }

    private fun setAdapter() {
        binding.reviewRecycler.apply {
            adapter = reviewAdapter
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }


    }

    private fun chipClickType() {
        binding.chipType.setOnCheckedChangeListener { group, selectedChipId ->
            val chip = group.findViewById<Chip>(selectedChipId)
            when (chip.text.toString().lowercase(Locale.ROOT)) {
                POSITIVE -> getReview(0)
                NEUTRALITY -> getReview(0)
                DENIAL -> getReview(0)
            }
        }
    }

    private fun getReview(number: Int) {
        lifecycleScope.launch {
            viewModel.getReview(number)
        }
    }

    private fun observeReviewData() {
        viewModel.reviewData.observe(viewLifecycleOwner) {
            it?.let { it1 -> reviewAdapter.setData(it1) }
        }
    }

    companion object {
        const val POSITIVE = "positive"
        const val NEUTRALITY = "neutrality"
        const val DENIAL = "denial"
    }

    override fun onclick(data: Review) {
        val args = Bundle()
        args.putParcelable("data", data)
        val dialogFragment = ReviewBottomSheetDialogFragment()
        dialogFragment.arguments = args
        dialogFragment.show(parentFragmentManager, "Sample Dialog Fragment")

    }
}