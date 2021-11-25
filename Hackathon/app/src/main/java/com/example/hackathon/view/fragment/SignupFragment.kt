package com.example.hackathon.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hackathon.R
import com.example.hackathon.data.pref.Pref
import com.example.hackathon.databinding.SignupFragmentBinding
import com.example.hackathon.domain.response.DataState
import com.example.hackathon.viewmodel.SignupViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class SignupFragment : Fragment() {

    private val viewModel: SignupViewModel by viewModels()
    private lateinit var binding: SignupFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SignupFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe()
        binding()
    }

    private fun binding() {
        binding.btnSignupSignup.setOnClickListener {
            if (checkInput()) {
                signup()
            } else {
                Toast.makeText(requireContext(), "입력이 잘못 되었습니다", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnToLoginSignup.setOnClickListener {
            findNavController().popBackStack(R.id.signupFragment, true)
            findNavController().navigate(R.id.action_signupFragment_to_clientLoginFragment)
        }
    }

    private fun checkInput(): Boolean {
        val pw = binding.edtPwSignup.text.toString()
        val pwAgain = binding.edtPwAgainSignup.text.toString()
        val name = binding.edtNameSignup.text.toString()
        if (pw != pwAgain) return false

        return true

    }

    private fun signup() {
        val email = arguments?.getString("email")!!
        val pw = binding.edtPwSignup.text.toString()
        val name = binding.edtNameSignup.text.toString()
        val isClient = binding.radioClientSignup.isChecked

        if (isClient) {
            viewModel.signupClient(email, pw, name)
        } else {
            viewModel.signupOwner(email, pw, name)
        }
    }


    private fun observe() {
        viewModel.clientSignupState.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Success -> {
                    Pref.token = it.data.token
                    Pref.isOwner = false
                    findNavController().navigate(R.id.action_signupFragment_to_clientMainFragment)
                }
                is DataState.Failure -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                is DataState.Loading -> {

                }
            }
        }

        viewModel.ownerSignupState.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Success -> {
                    Pref.token = it.data.token
                    Pref.isOwner = true
                    findNavController().navigate(R.id.action_signupFragment_to_ownerMainFragment)
                }
                is DataState.Failure -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                is DataState.Loading -> {

                }
            }
        }

    }
}