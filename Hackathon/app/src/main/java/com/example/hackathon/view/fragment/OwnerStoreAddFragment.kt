package com.example.hackathon.view.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.hackathon.R
import com.example.hackathon.base.BaseFragment
import com.example.hackathon.databinding.OwnerStoreAddFragmentBinding
import com.example.hackathon.domain.request.MenuRequest
import com.example.hackathon.domain.response.DataState
import com.example.hackathon.view.MainActivity
import com.example.hackathon.view.adapter.AddStoreMenuAdapter
import com.example.hackathon.view.adapter.RecyclerViewItemClickListener
import com.example.hackathon.viewmodel.OwnerStoreViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class OwnerStoreAddFragment :
    BaseFragment<OwnerStoreAddFragmentBinding>(R.layout.owner_store_add_fragment),

    RecyclerViewItemClickListener<MenuRequest> {
    private val viewModel: OwnerStoreViewModel by viewModels()
    val menuList = arrayListOf<MenuRequest>()
    lateinit var currentPhoto: String
    private lateinit var getResult: ActivityResultLauncher<Intent>
    lateinit var menuAdapter: AddStoreMenuAdapter
    override fun OwnerStoreAddFragmentBinding.onCreateView() {

    }

    override fun OwnerStoreAddFragmentBinding.onViewCreated() {

        getResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->

            try {
                if (result.resultCode == RESULT_OK) {
                    File(getPathFromUri(result?.data?.data))
                        .let { viewModel.postFile(it) }



                    binding.btnAddImageAddStore.setImageURI(result.data?.data)
                }
            } catch (e: Exception) {
                Log.d("TAG", "onCreateView: ${e}")
            }
        }
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

    @SuppressLint("Range")
    fun getPathFromUri(uri: Uri?): String? {
        val cursor: Cursor? =
            (activity as MainActivity).contentResolver.query(uri!!, null, null, null, null)
        cursor?.moveToNext()
        val path: String? = cursor?.getString(cursor.getColumnIndex("_data"))
        cursor?.close()
        Log.d("getPathFromUri", "getPathFromUri: ${path} ")
        return path
    }

    private fun init() {
        menuAdapter = AddStoreMenuAdapter(menuList, this)
    }


    fun bind() = with(binding) {
        binding.addBtn.setOnClickListener {
            constraintLayout3.transitionToEnd()
        }
        binding.btnAddImageAddStore.setOnClickListener {
            var writePermission =
                ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            var readPermission = ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            if (writePermission == PackageManager.PERMISSION_DENIED || readPermission == PackageManager.PERMISSION_DENIED) { // 권한 없어서 요청 ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE), REQ_STORAGE_PERMISSION) }

                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ),
                    1000
                )


            }else{
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = MediaStore.Images.Media.CONTENT_TYPE

                getResult.launch(intent)
            }


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
//            val photoUrl = menuList[0].photoUrl
            val category = binding.edtCategoryAddStore.text.toString()
            val tel = binding.edtTellAddStore.text.toString()
            val menus = menuList.toList()
            viewModel.postStore(name, address, category, "photoUrl", tel, menus)
            findNavController().navigateUp()
        }

    }


    override fun onclick(data: MenuRequest) {

    }

    fun addMenu(menu: MenuRequest) {
        menuList.add(menu)
        menuAdapter.notifyDataSetChanged()
    }
}