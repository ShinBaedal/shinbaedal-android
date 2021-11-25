package com.example.hackathon.view.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hackathon.R
import com.example.hackathon.base.BaseFragment
import com.example.hackathon.databinding.OwnerMenuFragmentBinding
import com.example.hackathon.domain.entity.Menu
import com.example.hackathon.domain.response.DataState
import com.example.hackathon.view.adapter.MenuAdapter
import com.example.hackathon.view.adapter.RecyclerViewItemClickListener
import com.example.hackathon.viewmodel.OwnerMenuViewModel

class OwnerMenuFragment(val onclick: RecyclerViewItemClickListener<Menu>) :
    BaseFragment<OwnerMenuFragmentBinding>(R.layout.owner_menu_fragment),
    RecyclerViewItemClickListener<com.example.hackathon.domain.entity.Menu> {
    private val viewModel: OwnerMenuViewModel by viewModels()
    private lateinit var menuListAdapter: MenuAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        bind()
        observe()
    }

    private fun init() {
        menuListAdapter = MenuAdapter(this)
    }

    private fun observe() {
        viewModel.menuState.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Success -> {
                    menuListAdapter.setList(it.data)
                }
                is DataState.Loading -> {
                    //shimmer
                }
                is DataState.Failure -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun bind() = with(binding) {
        rvMenuOwnerMenu.apply {
            adapter = menuListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }


    override fun onclick(data: Menu) {
        this.onclick(data)
    }
}