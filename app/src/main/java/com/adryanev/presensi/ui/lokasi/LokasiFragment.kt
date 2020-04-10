package com.adryanev.presensi.ui.lokasi


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.adryanev.presensi.R
import com.adryanev.presensi.databinding.FragmentLokasiBinding
import com.adryanev.presensi.utils.ToastHelper
import com.adryanev.presensi.utils.toast
import com.birjuvachhani.locus.Locus
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.koin.androidx.viewmodel.ext.android.viewModel

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
        subscribeUi(binding)

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        var mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY)
        if (mapViewBundle == null) {
            mapViewBundle = Bundle()
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle)
        }

        binding.mapView.onSaveInstanceState(mapViewBundle)
    }



    private fun subscribeUi(binding: FragmentLokasiBinding?) {

        //start Map


       Locus.startLocationUpdates(this){ result ->
           result.location?.let {location ->
               val lat = location.latitude.toString()
               val long = location.longitude.toString()
                binding?.latlong?.text = getString(R.string.latlong,lat,long)
               binding?.mapView?.getMapAsync {
                   it.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(location.latitude,location.longitude),18f))
                   it.addMarker(MarkerOptions().position(LatLng(location.latitude, location.longitude)).title(getString(R.string.lokasi_anda)))
               }

                requireContext().toast(ToastHelper.Info, "Current Location: lat=${lat} long=${long}")
           }
           result.error?.let {
               it.message?.let { it1 -> requireContext().toast(ToastHelper.Error, it1) }
           }
       }
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        binding.mapView.onStop()
    }

    override fun onPause() {
        binding.mapView.onPause();
        super.onPause()
    }

    override fun onDestroy() {
        binding.mapView.onLowMemory()
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }



}
