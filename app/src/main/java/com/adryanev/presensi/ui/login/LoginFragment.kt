package com.adryanev.presensi.ui.login

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.adryanev.presensi.databinding.FragmentLoginBinding
import org.jetbrains.anko.support.v4.toast
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class LoginFragment : Fragment() {

    val loginViewModel:LoginViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentLoginBinding.inflate(inflater,container, false);
        subscribeUI(binding)
        Timber.d("Login Fragment Creation")
        return binding.root
    }

    private fun subscribeUI(binding: FragmentLoginBinding?) {
        loginViewModel.uiModel.observe(viewLifecycleOwner, Observer {

            if(TextUtils.isEmpty(it.username)){
                binding?.loginUsername?.error = "Masukkan Username"
                binding?.loginUsername?.requestFocus()
            }
            if(TextUtils.isEmpty(it.password)){
                binding?.loginPassword?.error = "Masukkan Password"
                binding?.loginPassword?.requestFocus()
            }
        })

        loginViewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            toast(it?.data.toString())
        })
    }


}
