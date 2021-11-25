package com.example.hackathon.view.fragment

import android.net.Uri
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.hackathon.R
import com.example.hackathon.base.BaseFragment
import com.example.hackathon.base.toMultipartBody
import com.example.hackathon.databinding.OwnerStoreAddFragmentBinding
import com.example.hackathon.domain.entity.Menu
import com.example.hackathon.domain.request.MenuRequest
import com.example.hackathon.domain.response.DataState
import com.example.hackathon.view.adapter.AddStoreMenuAdapter
import com.example.hackathon.view.adapter.MenuAdapter
import com.example.hackathon.view.adapter.RecyclerViewItemClickListener
import com.example.hackathon.viewmodel.OwnerStoreViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.example.hackathon.view.dialog.OwnerStoreAddBottomSheetFragment

@AndroidEntryPoint
class OwnerStoreAddFragment :
    BaseFragment<OwnerStoreAddFragmentBinding>(R.layout.owner_store_add_fragment),
    RecyclerViewItemClickListener<MenuRequest> {
    private val viewModel: OwnerStoreViewModel by viewModels()
    val menuList = arrayListOf<MenuRequest>()
    lateinit var currentPhoto: String
    lateinit var menuAdapter: AddStoreMenuAdapter
    override fun OwnerStoreAddFragmentBinding.onCreateView() {

    }

    override fun OwnerStoreAddFragmentBinding.onViewCreated() {
        init()
        bind()
        observe()
    }

    private fun observe() = with(viewModel) {
        fileUploadState.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Success -> {
                    currentPhoto = it.data
                    binding.btnAddImageAddStore.load(it.data)
                }
                is DataState.Failure -> {
                }
                is DataState.Loading -> {
                }
            }
        }
    }

    private fun init() {
        menuAdapter = AddStoreMenuAdapter(menuList, this)
    }


    fun bind() = with(binding) {
        binding.addBtn.setOnClickListener {
            constraintLayout3.transitionToEnd()
        }
        binding.btnAddImageAddStore.setOnClickListener {
            getImage()
        }
        binding.btnAddMenuAddStore.setOnClickListener {
            val price = binding.edtMenuPriceAddStore.text.toString().toLong() //메뉴 가격
            val name = binding.edtNamePriceAddStore.text.toString() //메뉴 이름
            val newMenu = MenuRequest(price = price, name = name, photoUrl = currentPhoto)
            menuList.add(newMenu)
            binding.btnAddImageAddStore.load(R.drawable.ic_add_black_24dp)
            constraintLayout3.transitionToStart()
        }
        binding.btnAddStoreAddStore.setOnClickListener {
            val name = binding.edtStoreNameAddStore.text.toString()
            val address = binding.edtAddressAddStore.text.toString()
            val photoUrl = menuList[0].photoUrl
            val category = binding.edtCategoryAddStore.text.toString()
            val tel = binding.edtTellAddStore.text.toString()
            val menus = menuList.toList()
            viewModel.postStore(name, address, category, photoUrl, tel, menus)
            findNavController().navigateUp()
        }

    }

    fun getImage() = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let { viewModel.postFile(it) }
    }

    override fun onclick(data: MenuRequest) {
        menuList.remove(data)
        menuAdapter.notifyDataSetChanged()
    }

    fun addMenu(menu: MenuRequest) {
        menuList.add(menu)
        menuAdapter.notifyDataSetChanged()
    }
}