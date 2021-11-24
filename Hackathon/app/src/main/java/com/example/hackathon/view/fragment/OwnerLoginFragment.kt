package com.example.hackathon.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hackathon.R
import com.example.hackathon.viewmodel.OwnerLoginViewModel

class OwnerLoginFragment : Fragment() {


    private lateinit var viewModel: OwnerLoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.owner_login_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OwnerLoginViewModel::class.java)
        // TODO: Use the ViewModel
    }

}