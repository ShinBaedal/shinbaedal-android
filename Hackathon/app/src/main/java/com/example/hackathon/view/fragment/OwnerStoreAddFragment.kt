package com.example.hackathon.view.fragment

import com.example.hackathon.R
import com.example.hackathon.base.BaseFragment
import com.example.hackathon.databinding.OwnerStoreAddFragmentBinding
import com.example.hackathon.view.dialog.OwnerStoreAddBottomSheetFragment

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
    fun bind()= with(binding){
        binding.addBtn.setOnClickListener {
            constraintLayout3.transitionToEnd()
        }
        binding.btnAddMenuAddStore.setOnClickListener {
            binding.edtMenuPriceAddStore //메뉴 가겨
            binding.edtNamePriceAddStore //메뉴 이름

            constraintLayout3.transitionToStart()
        }
    }
}