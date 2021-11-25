package com.example.hackathon.view.fragment

import com.example.hackathon.R
import com.example.hackathon.base.BaseFragment
import com.example.hackathon.databinding.OwnerStoreAddFragmentBinding
import com.example.hackathon.view.dialog.OwnerStoreAddBottomSheetFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class OwnerStoreAddFragment :
    BaseFragment<OwnerStoreAddFragmentBinding>(R.layout.owner_store_add_fragment) {
    override fun OwnerStoreAddFragmentBinding.onCreateView() {

    }

    override fun OwnerStoreAddFragmentBinding.onViewCreated() {
        addButtonClick()
    }



    private fun addButtonClick() {
        val dialog = OwnerStoreAddBottomSheetFragment()
        binding.addBtn.setOnClickListener {
            dialog.show(parentFragmentManager, "dialog")
        }
    }
}