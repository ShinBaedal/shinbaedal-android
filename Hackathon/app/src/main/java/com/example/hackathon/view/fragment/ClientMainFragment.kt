package com.example.hackathon.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hackathon.data.pref.Pref
import com.example.hackathon.databinding.ClientMainFragmentBinding
import com.example.hackathon.domain.response.DataState
import com.example.hackathon.domain.entity.Store
import com.example.hackathon.view.adapter.RecyclerViewItemClickListener
import com.example.hackathon.view.adapter.StoreHorizontalAdapter
import com.example.hackathon.view.adapter.StoreVerticalAdapter
import com.example.hackathon.viewmodel.ClientMainViewModel

class ClientMainFragment : Fragment(), RecyclerViewItemClickListener<Store> {
    private val TAG = "ClientMainFragment"
    private val viewModel: ClientMainViewModel by viewModels()
    lateinit var binding: ClientMainFragmentBinding

    lateinit var horizontalStoreAdapter: StoreHorizontalAdapter
    lateinit var verticalStoreAdapter: StoreVerticalAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ClientMainFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        bind()
        observe()
    }

    private fun init() {
        horizontalStoreAdapter = StoreHorizontalAdapter(this)
        verticalStoreAdapter = StoreVerticalAdapter(this)
    }

    private fun observe() {
        viewModel.getStoresState.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Success -> {
                    verticalStoreAdapter.setItem(it.data)
                }
                is DataState.Loading -> {

                }
                is DataState.Failure -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun bind() {
        binding.btnAddressClientMain.setOnClickListener { patchAddress() }
        binding.btnLogoutClientMain.setOnClickListener { logout() }
        binding.rvRecommendClientMain.apply {
            adapter = horizontalStoreAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        binding.rvStoresClientMain.apply {
            adapter = verticalStoreAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun logout() {
        Pref.token = null
        findNavController().navigateUp()
    }

    private fun patchAddress() {
        viewModel.patchAddress("")
    }

    override fun onResume() {
        super.onResume()
        viewModelStore.clear()
    }


    override fun onclick(data: Store) {
        //navigate to detail
        findNavController()
    }


}