package com.example.hackathon.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hackathon.R
import com.example.hackathon.data.pref.Pref
import com.example.hackathon.databinding.EmailAuthFragmentBinding
import com.example.hackathon.domain.response.DataState
import com.example.hackathon.viewmodel.SignupViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class EmailAuthFragment : Fragment() {
    private val viewModel: SignupViewModel by viewModels()
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
        observe()
    }

    private fun checkInput(): Boolean {
        val email: String = binding.edtEmailEmailAuth.text.toString()
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

        val check1 = email.isNotEmpty()
        val check2 = email.matches(emailPattern.toRegex())

        return check1 && check2
    }

    private fun bind() {
        binding.btnNextEmailAuth.setOnClickListener {
            val code = binding.edtAuthCodeEmailAuth.text.toString().toInt()
            viewModel.checkCode(code)
        }
        binding.btnRequestAuthEmailAuth.setOnClickListener {
            if (checkInput()) {
                val email = binding.edtEmailEmailAuth.text.toString()
                viewModel.requestEmailAuth(email)
            } else {
                binding.edtEmailEmailAuth.error = "이메일 형식을 확인해주세요"
            }
        }
    }

    private fun observe() {
        viewModel.emailAuthState.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Success -> {
                    val email = binding.edtEmailEmailAuth.text.toString()
                    val bundle = bundleOf("email" to email)
                    findNavController().navigate(
                        R.id.action_emailAuthFragment_to_clientSignupFragment,
                        bundle
                    )
                }
                is DataState.Failure -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                }
                is DataState.Loading -> {
                    binding.edtEmailEmailAuth.isEnabled = false
                    binding.containerAuthCodeEmailAuth.visibility = View.VISIBLE
                }
            }
        }
    }


}