package com.example.hackathon.view.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hackathon.R
import com.example.hackathon.data.pref.Pref
import com.example.hackathon.databinding.ClientMainFragmentBinding
import com.example.hackathon.domain.entity.Me
import com.example.hackathon.domain.response.DataState
import com.example.hackathon.domain.entity.Store
import com.example.hackathon.view.adapter.RecyclerViewItemClickListener
import com.example.hackathon.view.adapter.StoreHorizontalAdapter
import com.example.hackathon.view.adapter.StoreVerticalAdapter
import com.example.hackathon.viewmodel.ClientMainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class ClientMainFragment : Fragment(), RecyclerViewItemClickListener<Store> {
    private val TAG = "ClientMainFragment"
    private val viewModel: ClientMainViewModel by viewModels()
    lateinit var binding: ClientMainFragmentBinding

    lateinit var horizontalStoreAdapter: StoreHorizontalAdapter
    lateinit var verticalStoreAdapter: StoreVerticalAdapter

    lateinit var me: Me

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
        viewModel.getMyInfo()
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
        viewModel.myInfoState.observe(requireActivity()) {
            me = it
            binding.btnAddressClientMain.text = me.address
            binding.tvName.text = me.name
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
        binding.fabClientMain.setOnClickListener {

        }
        binding.chipChickenClientMain.setOnCloseIconClickListener {
            viewModel.getStores(
                "치킨",
                me.address
            )
        }
        binding.chipChineseClientMain.setOnCloseIconClickListener {
            viewModel.getStores(
                "중식",
                me.address
            )
        }
        binding.chipHamburgerClientMain.setOnCloseIconClickListener {
            viewModel.getStores(
                "햄버거",
                me.address
            )
        }
        binding.chipJokboClientMain.setOnCloseIconClickListener {
            viewModel.getStores(
                "족보",
                me.address
            )
        }
        binding.chipJapaneseClientMain.setOnCloseIconClickListener {
            viewModel.getStores(
                "일식",
                me.address
            )
        }
        binding.chipPizzaClientMain.setOnCloseIconClickListener {
            viewModel.getStores(
                "피자",
                me.address
            )
        }
        binding.chipRiceClientMain.setOnCloseIconClickListener {
            viewModel.getStores(
                "도시락",
                me.address
            )
        }
    }

    private fun logout() {
        Pref.token = null
        findNavController().popBackStack(R.id.clientMainFragment, true)
        findNavController().navigateUp()
    }

    private fun patchAddress() {
        val ad = AlertDialog.Builder(requireContext());
        ad.setMessage("주소를 입력하세요");

        // EditText 삽입하기
        val et = EditText(requireContext());
        val container = FrameLayout(requireContext());
        val params = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.leftMargin = 50;
        params.rightMargin = 50;
        et.layoutParams = params;
        container.addView(et);
        ad.setView(container);

        ad.setPositiveButton("변경", object : DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {
                val value = et.text.toString();
                if (value.trim().equals("")) {
                    Toast.makeText(requireContext(), "주소를 입력해주세요", Toast.LENGTH_SHORT)
                        .show();
                    return;
                }
                p0?.dismiss()
                viewModel.patchAddress(value)
                binding.btnAddressClientMain.text = value
            }
        })

        // 취소 버튼 설정
        ad.setNegativeButton(
            "취소"
        ) { p0, p1 -> p0?.dismiss() };
        ad.show();

    }

    override fun onResume() {
        super.onResume()
        viewModelStore.clear()
    }


    override fun onclick(data: Store) {
        //navigate to detail
        findNavController().navigate(R.id.action_clientMainFragment_to_clientDetailFragment)
    }


}