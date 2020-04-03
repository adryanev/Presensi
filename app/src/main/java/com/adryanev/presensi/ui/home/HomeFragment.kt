package com.adryanev.presensi.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.adryanev.presensi.R
import com.adryanev.presensi.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        Timber.d("Masuk HomeFragment")
        subscribeUI(binding);


        return binding.root
    }

    private fun subscribeUI(binding: FragmentHomeBinding){
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            binding.textHome.text = it
        })

        homeViewModel.isLogin.observe(viewLifecycleOwner, Observer {
            if(it == false){
                val directions = HomeFragmentDirections.actionNavHomeToLoginFragment();
                findNavController().navigate(directions,NavOptions.Builder().setPopUpTo(R.id.nav_home,true).build());
            }
        })
    }
}
