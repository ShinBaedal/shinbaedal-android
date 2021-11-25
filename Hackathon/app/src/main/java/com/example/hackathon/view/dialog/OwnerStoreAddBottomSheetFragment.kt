package com.example.hackathon.view.dialog

import android.text.TextUtils
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.hackathon.R
import com.example.hackathon.base.BaseDialogFragment
import com.example.hackathon.databinding.OwnerStoreAddBottomSheetDialogBinding

class OwnerStoreAddBottomSheetFragment :
    BaseDialogFragment<OwnerStoreAddBottomSheetDialogBinding>(R.layout.owner_store_add_bottom_sheet_dialog) {

    override fun OwnerStoreAddBottomSheetDialogBinding.onCreateView() {

    }

    override fun OwnerStoreAddBottomSheetDialogBinding.onViewCreated() {

    }

    private fun sendData() {

        if (textNullTest()) {
            findNavController().navigate(R.id.action_ownerStoreAddBottomSheetDialog_to_ownerStoreAddFragment)
        } else {
            Toast.makeText(requireContext(), "빈칸을 채워주세요", Toast.LENGTH_SHORT).show()
        }

    }

    private fun textNullTest() =
        !(TextUtils.isEmpty(binding.menuNameTxt.toString())) && !(TextUtils.isEmpty(binding.menuPriceTxt.toString()))




}