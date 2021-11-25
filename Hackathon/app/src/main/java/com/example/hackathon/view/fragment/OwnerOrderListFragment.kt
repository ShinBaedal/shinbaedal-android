package com.example.hackathon.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hackathon.R
import com.example.hackathon.base.BaseFragment
import com.example.hackathon.databinding.OwnerOrderListFragmentBinding
import com.example.hackathon.domain.entity.Order
import com.example.hackathon.domain.response.DataState
import com.example.hackathon.view.adapter.OrderAdapter
import com.example.hackathon.view.adapter.RecyclerViewItemClickListener
import com.example.hackathon.viewmodel.OwnerOrderListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OwnerOrderListFragment :
    BaseFragment<OwnerOrderListFragmentBinding>(R.layout.owner_order_list_fragment),
    RecyclerViewItemClickListener<Order> {
    private val viewModel: OwnerOrderListViewModel by viewModels()
    private val orderAdapter: OrderAdapter by lazy { OrderAdapter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
        observe()
        //viewModel.getOrders()
    }

    private fun bind() = with(binding) {
        btnBackOwnerOrder.setOnClickListener { findNavController().navigateUp() }
        rvOrderOwnerOrder.apply {
            adapter = orderAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observe() = with(viewModel) {
        viewModel.orderState.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Success -> {
                    orderAdapter.setList(it.data)
                }
                is DataState.Failure -> {

                }
                is DataState.Loading -> {

                }
            }
        }
    }

    override fun onclick(data: Order) {
        if (!data.isDone) {
            viewModel.doOrder(data.id)
            data.isDone = true
        }
    }


}