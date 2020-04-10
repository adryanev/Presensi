package com.adryanev.presensi.ui.lokasi

import android.Manifest
import android.os.Build
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel


import com.adryanev.presensi.R
import com.adryanev.presensi.databinding.FragmentLokasiBinding
import com.adryanev.presensi.utils.ToastHelper
import com.adryanev.presensi.utils.toast
import com.birjuvachhani.locus.Locus
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

class LokasiFragment : Fragment() {

    val viewModel: LokasiViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLokasiBinding.inflate(inflater, container, false)
        subscribeUi(binding)

        return binding.root
    }



    private fun subscribeUi(binding: FragmentLokasiBinding?) {

       Locus.startLocationUpdates(this){ result ->
           result.location?.let {
               val lat = it.latitude.toString()
               val long = it.longitude.toString()
                binding?.latlong?.text = getString(R.string.latlong,lat,long)

                requireContext().toast(ToastHelper.Info, "Current Location: lat=${lat} long=${long}")
           }
           result.error?.let {
               it.message?.let { it1 -> requireContext().toast(ToastHelper.Error, it1) }
           }
       }
    }

}
