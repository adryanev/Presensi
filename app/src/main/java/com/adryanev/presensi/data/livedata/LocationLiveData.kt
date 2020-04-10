package com.adryanev.presensi.data.livedata

import android.content.Context
import android.location.Location
import androidx.lifecycle.LiveData
import com.adryanev.presensi.data.models.location.LocationModel
import com.google.android.gms.location.*
import org.koin.core.KoinComponent
import org.koin.core.inject

class LocationLiveData: LiveData<LocationModel>(), KoinComponent {

    private val fusedLocationProviderClient:FusedLocationProviderClient by inject()

    private fun setLocationData(location: Location){
        value = LocationModel(latitude = location.latitude, longitude = location.longitude)
    }

    companion object {
        val locationRequest: LocationRequest = LocationRequest.create().apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        }
    }

    private val locationCallback = object:LocationCallback() {

        override fun onLocationResult(locationResult: LocationResult?) {
            locationResult?: return
            for(location in locationResult.locations){
                setLocationData(location)
            }
        }

    }

    private fun startLocationUpdates(){
        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest, locationCallback, null
        )
    }

    override fun onActive() {
        super.onActive()
        fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
            location?.also {
                setLocationData(it)
            }
        }

        startLocationUpdates()
    }
}