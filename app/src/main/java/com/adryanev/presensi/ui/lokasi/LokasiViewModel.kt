package com.adryanev.presensi.ui.lokasi

import android.content.Context
import androidx.lifecycle.*
import androidx.work.*
import com.adryanev.presensi.data.firestore.FirestoreRepository
import com.adryanev.presensi.utils.preference.PreferenceLokasi
import com.adryanev.presensi.utils.preference.PreferenceProfil
import com.adryanev.presensi.workmanager.SEND_LOCATION_WORKNAME
import com.adryanev.presensi.workmanager.SendLocationWorker
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber
import java.util.concurrent.TimeUnit

class LokasiViewModel(val firestoreRepository: FirestoreRepository, val lokasiPreference: PreferenceLokasi, val profilPreference: PreferenceProfil): ViewModel(),KoinComponent {

    val context: Context by inject()

    val isOn = lokasiPreference.isOn

    val lokasi = firestoreRepository.getLocationData(profilPreference.username!!)


    fun turnOnLocation(){
        Timber.d("Turning on Location")
        val constraint = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val sendLocationWorker =
            PeriodicWorkRequestBuilder<SendLocationWorker>(15, TimeUnit.MINUTES).setConstraints(
                constraint
            ).addTag(SendLocationWorker.TAG).setInitialDelay(1, TimeUnit.MINUTES).build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            SEND_LOCATION_WORKNAME,
            ExistingPeriodicWorkPolicy.KEEP,
            sendLocationWorker
        )
        lokasiPreference.on = true
    }

    fun turnOffLocation(){
        Timber.d("Turning off Location")
        val status = WorkManager.getInstance(context).cancelUniqueWork(
            SEND_LOCATION_WORKNAME
        )
        lokasiPreference.on = false
    }


}
