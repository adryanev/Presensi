package com.adryanev.presensi.workmanager

import android.content.Context
import android.location.Location
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.adryanev.presensi.data.firebase.FirebaseRepository
import com.adryanev.presensi.data.firebase.LocationModel
import com.adryanev.presensi.utils.preference.PreferenceProfil
import com.birjuvachhani.locus.Locus
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class SendLocationWorker(val context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters),KoinComponent {

    private val firebaseRepository: FirebaseRepository by inject()
    companion object{
        val TAG = "SendLocationWorker"
    }
    override suspend fun doWork(): Result {
        Timber.d("SendLocation Worker Started")
        var success = false
        val time = System.currentTimeMillis()

        val loc:Location =  withContext(Dispatchers.IO) {
            getLocation()
        }
        val lokasi = LocationModel(loc.latitude, loc.longitude, time)

        //send to db
        success = firebaseRepository.insertLokasi(lokasi)

        if(!success){
            return Result.failure()
        }
        return Result.success()

    }

    private suspend fun getLocation(): Location  = suspendCoroutine { continuation ->

        Locus.getCurrentLocation(context) {result->
            result.location?.let {
                Timber.d("Location received!")
                continuation.resume(it)
            }

            result.error?.let {
                Timber.d(it)
                continuation.resumeWithException(it)
            }
        }
    }

}