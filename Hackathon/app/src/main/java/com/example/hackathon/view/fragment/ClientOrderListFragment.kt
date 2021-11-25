package com.example.hackathon.view.fragment

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.hackathon.R
import com.example.hackathon.base.BaseFragment
import com.example.hackathon.data.pref.Pref
import com.example.hackathon.databinding.ClientOrderListFragmentBinding
import com.example.hackathon.viewmodel.ClientOrderListViewModel

class ClientOrderListFragment :
    BaseFragment<ClientOrderListFragmentBinding>(R.layout.client_order_list_fragment) {
    private val viewModel: ClientOrderListViewModel by viewModels()
    private val URL = "https://amazing-turing-24d007.netlify.app?access_token="
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.webClientOrderList.apply {
            loadUrl(URL + Pref.token)
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            addJavascriptInterface(WebAppInterface(requireContext()),"Android")
        }
    }

    inner class WebAppInterface(private val mContext: Context) {

        /** Show a toast from the web page  */
        @JavascriptInterface
        fun showToast(toast: String) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show()
        }

        @JavascriptInterface
        fun navigateUp(){
            findNavController().navigateUp()
        }
    }
}