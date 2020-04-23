package com.adryanev.presensi.workmanager

import android.content.Context
import android.location.Location
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.adryanev.presensi.data.firestore.FirestoreRepository
import com.adryanev.presensi.data.firestore.LocationModel
import com.adryanev.presensi.utils.LocationHelper
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class SendLocationWorker(val context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters), KoinComponent {

    private val fusedLocationProviderClient: FusedLocationProviderClient by inject()
    private val firestoreRepository: FirestoreRepository by inject()

    companion object {
        val TAG = "SendLocationWorker"
    }

    override suspend fun doWork(): Result {
        Timber.d("SendLocation Worker Started")
        var success: Boolean
        val time = System.currentTimeMillis()

        val loc: Location = withContext(Dispatchers.IO) {
            LocationHelper.getLocation()
        }
        val lokasi = LocationModel(loc.latitude, loc.longitude, time)

        //send to db

        success = withContext(Dispatchers.IO) { firestoreRepository.insertLokasi(lokasi) }

        if (!success) {
            return Result.failure()
        }
        return Result.success()

    }


}