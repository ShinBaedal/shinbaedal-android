package com.example.hackathon.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hackathon.R
import com.example.hackathon.viewmodel.OwnerSignupViewModel

class OwnerSignupFragment : Fragment() {
    private lateinit var viewModel: OwnerSignupViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.owner_signup_fragment, container, false)
    }


}