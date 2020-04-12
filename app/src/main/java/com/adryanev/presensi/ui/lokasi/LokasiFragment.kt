package com.adryanev.presensi.ui.lokasi


import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.adryanev.presensi.R
import com.adryanev.presensi.databinding.FragmentLokasiBinding
import com.adryanev.presensi.utils.ToastHelper
import com.adryanev.presensi.utils.toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import org.jetbrains.anko.sdk25.coroutines.onCheckedChange
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class LokasiFragment : Fragment(){

    val viewModel: LokasiViewModel by viewModel()
    private lateinit var binding: FragmentLokasiBinding
    companion object{
        val MAPVIEW_BUNDLE_KEY = "MapViewBundleKey"
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLokasiBinding.inflate(inflater, container, false)
        var mapViewBundle: Bundle? = null
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY)
        }
        binding.mapView.onCreate(mapViewBundle)

        binding.vm = viewModel
        runPerission()
        return binding.root
    }

    val dexterReportListener =object :MultiplePermissionsListener{
        override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
            report?.let {
                if(it.areAllPermissionsGranted()){
                    subscribeUi(binding)
                }
                if(it.isAnyPermissionPermanentlyDenied){
                    requireContext().toast(ToastHelper.Error,"Harus menyetujui permission")
                }

            }
        }

        override fun onPermissionRationaleShouldBeShown(
            permissions: MutableList<PermissionRequest>?,
            token: PermissionToken?
        ) {
            token?.continuePermissionRequest()
        }

    }
    private fun runPerission() {

        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q){
            Dexter.withActivity(requireActivity()).withPermissions(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION
            ).withListener(dexterReportListener).withErrorListener {
                requireContext().toast(ToastHelper.Error,it.toString())
                findNavController().popBackStack(R.id.nav_home,true)
            }.check()
        }
        else{
            Dexter.withActivity(requireActivity()).withPermissions(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ).withListener(dexterReportListener).withErrorListener {
                requireContext().toast(ToastHelper.Error,it.toString())
                findNavController().popBackStack(R.id.nav_home,true)

            }.check()
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        var mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY)
        if (mapViewBundle == null) {
            mapViewBundle = Bundle()
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle)
        }

        binding.mapView.onSaveInstanceState(mapViewBundle)
    }

    override fun onResume() {
        super.onResume()
        subscribeUi(binding)
    }



    private fun subscribeUi(binding: FragmentLokasiBinding?) {

        viewModel.lokasi.observe(viewLifecycleOwner, Observer { data ->
            data?.let { location ->
                val marker = MarkerOptions().position(LatLng(location.latitude, location.longitude)).title(getString(R.string.lokasi_anda));
                Timber.d("Lokasi Sekarang = ${location.latitude}, ${location.longitude}")
                binding?.mapView?.getMapAsync {
                    it.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(location.latitude,location.longitude),18f))
                    it.addMarker(marker)

                }

            }

        })

        viewModel.isOn.observe(viewLifecycleOwner, Observer { isOn ->
            val lokasiSwitch = binding?.lokasiSwitch
            lokasiSwitch?.isChecked = isOn

                binding?.lokasiSwitch?.onCheckedChange { buttonView, isChecked ->
                   if(buttonView!!.isPressed){
                       if(isChecked){
                           viewModel.turnOnLocation()
                       }
                       else{
                           viewModel.turnOffLocation()
                       }
                   }
                }

        })

    }




}
