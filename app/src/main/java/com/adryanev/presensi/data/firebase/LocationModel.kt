package com.adryanev.presensi.data.firebase

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class LocationModel (val latitude: Double, val longitude: Double, val time: Long)