package com.example.hackathon.view.fragment

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.example.hackathon.R
import com.example.hackathon.base.BaseFragment
import com.example.hackathon.databinding.OwnerMainFragmentBinding
import com.example.hackathon.viewmodel.OwnerMainViewModel

class OwnerMainFragment : BaseFragment<OwnerMainFragmentBinding>(R.layout.owner_main_fragment) {

    private val viewModel: OwnerMainViewModel by viewModels()

    fun getImage() = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->

    }
    override fun OwnerMainFragmentBinding.onViewCreated() {


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }




}