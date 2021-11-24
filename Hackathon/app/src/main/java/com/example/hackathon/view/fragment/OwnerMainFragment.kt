package com.example.hackathon.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hackathon.R
import com.example.hackathon.viewmodel.OwnerMainViewModel

class OwnerMainFragment : Fragment() {

    companion object {
        fun newInstance() = OwnerMainFragment()
    }

    private lateinit var viewModel: OwnerMainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.owner_main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OwnerMainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}