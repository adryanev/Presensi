package com.adryanev.presensi.utils

import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object LocationHelper: KoinComponent{

    private val fusedLocationProviderClient: FusedLocationProviderClient by inject()

    suspend fun getLocation(): Location = suspendCoroutine { continuation ->

        fusedLocationProviderClient.lastLocation.addOnSuccessListener {
            continuation.resume(it)
        }
            .addOnFailureListener {
                Timber.e(it)
                continuation.resumeWithException(it)
            }
    }
}