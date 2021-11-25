package com.example.hackathon.view.fragment

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.hackathon.R
import com.example.hackathon.base.BaseFragment
import com.example.hackathon.databinding.ClientDetailFragmentBinding
import com.example.hackathon.viewmodel.ClientDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClientDetailFragment :
    BaseFragment<ClientDetailFragmentBinding>(R.layout.client_detail_fragment) {

    private val viewModel: ClientDetailViewModel by viewModels()

    private val url = ""
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
    }

    private fun bind() {
        binding.webClientDetail.apply {
            loadUrl(url!!)
            settings.javaScriptEnabled = true
            addJavascriptInterface(WebAppInterface(requireContext()),"Android")

        }

    }


    class WebAppInterface(private val mContext: Context) {
        @JavascriptInterface
        fun showToast(toast: String) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show()
        }

    }

}