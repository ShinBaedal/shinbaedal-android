package com.example.hackathon.view.fragment

import android.graphics.Color
import android.net.Uri
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hackathon.R
import com.example.hackathon.adapter.OwnerMainAdapter
import com.example.hackathon.base.BaseFragment
import com.example.hackathon.base.toMultipartBody
import com.example.hackathon.databinding.OwnerMainFragmentBinding
import com.example.hackathon.domain.entity.Store
import com.example.hackathon.domain.response.DataState
import com.example.hackathon.view.adapter.RecyclerViewItemClickListener
import com.example.hackathon.viewmodel.OwnerMainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class OwnerMainFragment : BaseFragment<OwnerMainFragmentBinding>(R.layout.owner_main_fragment),
    RecyclerViewItemClickListener<Store> {

    private val viewModel: OwnerMainViewModel by viewModels()

//    private val ssb: SpannableStringBuilder by lazy {
//        SpannableStringBuilder("UserMain")
//    }
    private val ownerMainAdapter: OwnerMainAdapter by lazy {
        OwnerMainAdapter(this)
    }


    override fun OwnerMainFragmentBinding.onViewCreated() {
        setAdapter()
        observe()
        getData()
        bind()
        addStoreOnClick()
    }

    private fun bind() = with(binding){
        fabOwnerMain.setOnClickListener {
            findNavController().navigate(R.id.action_ownerMainFragment_to_ownerOrderListFragment)
        }
    }


    private fun setAdapter() {
        binding.ownerMainRecyclerView.apply {
            adapter = ownerMainAdapter
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        }
    }

    private fun observe() = with(viewModel) {
        storesState.observe(viewLifecycleOwner) {
            if (it is DataState.Success) {
                Log.d("ownermain",it.data.toString())
                ownerMainAdapter.setList(it.data)
            }
        }
    }

    private fun getData() {
        viewModel.getStores()
    }

    override fun onclick(data: Store) {
        val bundle = bundleOf("storeId" to data.id)
        findNavController().navigate(R.id.action_ownerMainFragment_to_ownerDetailFragment, bundle)
    }


    private fun addStoreOnClick(){
        binding.addStoreLayout.setOnClickListener {
           findNavController().navigate(R.id.action_ownerMainFragment_to_ownerStoreAddFragment)
        }
    }

}

