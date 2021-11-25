package com.example.hackathon.view.dialog

import com.example.hackathon.R
import com.example.hackathon.base.BaseDialogFragment
import com.example.hackathon.databinding.OwenrReviewBottomSheetDialogBinding
import android.os.Bundle
import com.example.hackathon.domain.entity.Review


class ReviewBottomSheetDialogFragment :
    BaseDialogFragment<OwenrReviewBottomSheetDialogBinding>(R.layout.owenr_review_bottom_sheet_dialog) {

    override fun OwenrReviewBottomSheetDialogBinding.onCreateView() {
        val mArgs = arguments
        val data = mArgs?.getString("data") as Review
        binding.review = data
        binding.reply = data.reply[0]
        binding.user = data.user

    }

    override fun OwenrReviewBottomSheetDialogBinding.onViewCreated() {
    }
}