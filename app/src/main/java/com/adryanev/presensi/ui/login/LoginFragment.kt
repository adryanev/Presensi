package com.adryanev.presensi.ui.login

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import com.adryanev.presensi.databinding.FragmentLoginBinding
import com.adryanev.presensi.utils.ToastHelper
import com.adryanev.presensi.utils.api.ResponseResult
import com.adryanev.presensi.utils.toast
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
        binding.vm = loginViewModel
        subscribeUI(binding)
        Timber.d("Login Fragment Creation")
        return binding.root
    }

    private fun subscribeUI(binding: FragmentLoginBinding?) {
        loginViewModel.loginSuccessControl.observe(viewLifecycleOwner, Observer {
            when(it){
                is ResponseResult.Success ->{
                    requireContext().toast(ToastHelper.Success,"Berhasil Login")
                    val direction = LoginFragmentDirections.actionLoginFragmentToNavHome()
                    findNavController().navigate(direction)
                }
                is ResponseResult.Loading -> {
                    binding?.progressBar?.visibility = View.VISIBLE
                    Timber.d("Loading data")
                }
                is ResponseResult.Complete -> {
                    binding?.progressBar?.visibility = View.GONE
                    Timber.d("Finished Load data")
                }
                is ResponseResult.Error -> {
                    requireContext().toast(ToastHelper.Error,"Gagal login: "+it.exception)
                }
            }


        })
        //mediator livedata trick to wiring 2 live data
        loginViewModel.mediatorLiveData.observe(viewLifecycleOwner, Observer {  })
        loginViewModel.toastMessage.observe(viewLifecycleOwner, Observer {
            if(it != null){
                val message = it.format(requireContext())
                requireContext().toast(ToastHelper.Info, message)
            }

        })
    }


}
