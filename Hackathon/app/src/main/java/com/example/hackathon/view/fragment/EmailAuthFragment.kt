package com.example.hackathon.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hackathon.R
import com.example.hackathon.databinding.EmailAuthFragmentBinding
import com.example.hackathon.viewmodel.EmailAuthViewModel

class EmailAuthFragment : Fragment() {
    private val viewModel: EmailAuthViewModel by viewModels()
    private lateinit var binding: EmailAuthFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = EmailAuthFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bind()
    }

    private fun bind() {
        val type = bundleOf("type" to arguments?.get("type"))

        binding.btnNextEmailAuth.setOnClickListener {
            findNavController().navigate(
                R.id.action_emailAuthFragment_to_clientSignupFragment,
                type
            )
        }
        binding.btnRequestAuthEmailAuth.setOnClickListener {
            //request email
        }


    }


}