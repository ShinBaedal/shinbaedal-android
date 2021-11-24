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
import com.example.hackathon.databinding.ClientLoginFragmentBinding
import com.example.hackathon.domain.response.DataState
import com.example.hackathon.viewmodel.LoginViewModel

class ClientLoginFragment : Fragment() {
    lateinit var binding: ClientLoginFragmentBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ClientLoginFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
    }

    private fun login() {
        val email = binding.edtEmailOwnerLogin.text.toString()
        val pw = binding.edtPwClientLogin.text.toString()

        viewModel.loginClient(email, pw)
    }

    private fun bind() {
        binding.btnToClientOwnerLogin.setOnClickListener {
            findNavController().navigate(R.id.action_clientLoginFragment_to_ownerLoginFragment)
        }
        binding.btnToSignupOwnerLogin.setOnClickListener {
            findNavController().navigate(R.id.action_clientLoginFragment_to_emailAuthFragment)
        }
        binding.btnLoginOwnerLogin.setOnClickListener {

        }
    }

    private fun observe() {
        viewModel.loginState.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Success -> {
                    Pref.token = it.data.token
                    Pref.isOwner = false
                }
                is DataState.Failure -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                is DataState.Loading -> {

                }
            }
        }
    }

    fun navigateToOwnerMain() {
        findNavController().navigate(R.id.action_clientLoginFragment_to_ownerLoginFragment)
    }


}