package com.adryanev.presensi.data.firestore

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class LocationModel(
    var latitude: Double = 0.0,
    var longitude: Double = 0.0,
    var time: Long = 0
) {
    companion object {
        val LATITUDE = "latitude"
        val LONGITUDE = "longitude"
        val TIME = "time"
    }
}