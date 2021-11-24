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
import com.example.hackathon.databinding.OwnerLoginFragmentBinding
import com.example.hackathon.domain.response.DataState
import com.example.hackathon.viewmodel.LoginViewModel

class OwnerLoginFragment : Fragment() {
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: OwnerLoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = OwnerLoginFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
        observe()
    }

    override fun onResume() {
        super.onResume()
        viewModelStore.clear()
    }

    private fun login() {
        val email = binding.edtEmailOwnerLogin.text.toString()
        val pw = binding.edtPwOwnerLogin.text.toString()

        viewModel.loginClient(email, pw)
    }

    private fun bind() {
        binding.btnLoginOwnerLogin.setOnClickListener {
            login()
        }
        binding.btnToClientOwnerLogin.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.btnToSignupOwnerLogin.setOnClickListener {
            findNavController().navigate(R.id.action_ownerLoginFragment_to_emailAuthFragment)
        }
    }

    private fun observe() {
        viewModel.loginState.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Success -> {
                    Pref.token = it.data.token
                    Pref.isOwner = true

                    navigateToOwnerMain()
                }
                is DataState.Failure -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                is DataState.Loading -> {

                }
            }
        }
    }

    private fun navigateToOwnerMain() {
        findNavController().navigate(R.id.action_ownerLoginFragment_to_ownerMainFragment)
    }

}