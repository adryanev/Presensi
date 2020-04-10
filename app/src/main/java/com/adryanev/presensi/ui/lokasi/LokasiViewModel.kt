package com.adryanev.presensi.ui.lokasi

import android.content.Context
import androidx.databinding.ObservableInt
import androidx.lifecycle.*
import com.adryanev.presensi.R
import com.adryanev.presensi.data.livedata.LocationLiveData
import com.adryanev.presensi.data.models.location.LocationModel
import com.birjuvachhani.locus.Locus
import java.util.*

class LokasiViewModel: ViewModel() {

    val location: LocationLiveData = LocationLiveData()
    val latitude = Transformations.map(location){
        it.latitude
    }
    val longitude = Transformations.map(location){
        it.longitude
    }

}
