package com.example.hackathon.view.fragment

import android.graphics.Color
import android.net.Uri
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hackathon.R
import com.example.hackathon.adapter.OwnerMainAdapter
import com.example.hackathon.base.BaseFragment
import com.example.hackathon.base.toMultipartBody
import com.example.hackathon.databinding.OwnerMainFragmentBinding
import com.example.hackathon.databinding.OwnerStoreAddBottomSheetDialogBinding
import com.example.hackathon.domain.response.DataState
import com.example.hackathon.view.dialog.OwnerStoreAddBottomSheetFragment
import com.example.hackathon.view.dialog.OwnerStoreAddBottomSheetFragmentDirections
import com.example.hackathon.viewmodel.OwnerMainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class OwnerMainFragment : BaseFragment<OwnerMainFragmentBinding>(R.layout.owner_main_fragment) {

    private val viewModel: OwnerMainViewModel by viewModels()

    private val ssb: SpannableStringBuilder by lazy {
        SpannableStringBuilder("UserMain")
    }
    private val ownerMainAdapter: OwnerMainAdapter by lazy {
        OwnerMainAdapter()
    }


    override fun OwnerMainFragmentBinding.onViewCreated() {
        setAdapter()
        observe()
        getData()
        addStoreClick()
    }

    override fun OwnerMainFragmentBinding.onCreateView() {
        ssb.apply {
            setSpan(
                ForegroundColorSpan(Color.BLACK),
                0,
                3,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
    }


    private fun setAdapter() {
        binding.ownerMainRecyclerView.apply {
            adapter = ownerMainAdapter
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        }
    }

    fun addStoreClick() {
        binding.addStoreLayout.setOnClickListener {
            val dialog = OwnerStoreAddBottomSheetFragment()
            dialog.show(parentFragmentManager, "OwnerStoreAddBottomSheetFragment")
        }
    }

    private fun observe() = with(viewModel) {
        storesState.observe(viewLifecycleOwner) {
            if (it is DataState.Success) {
                ownerMainAdapter.setList(it.data)
            }
        }
    }

    private fun getData() {
        viewModel.getStores()
    }


}

