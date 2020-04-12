package com.adryanev.presensi.data.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.adryanev.presensi.data.firestore.LocationModel
import com.google.firebase.firestore.*
import com.google.protobuf.DoubleValue
import timber.log.Timber

class FirestoreLiveData(val document: DocumentReference) : LiveData<LocationModel>() {
    val model  = LocationModel()
    override fun onActive() {
        super.onActive()
        document.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Timber.w(e)
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {
                Timber.d("Current data: ${snapshot.data}")

                val lat: Double = snapshot.getDouble(LocationModel.LATITUDE)!!
                val long = snapshot.getDouble(LocationModel.LONGITUDE)!!
                val time: Long = snapshot.getLong(LocationModel.TIME)!!
                model.apply {
                    latitude = lat
                    longitude = long
                    this.time = time
                }

            } else {
                Timber.d("Current data: null")
            }
            value =model
        }
    }

}