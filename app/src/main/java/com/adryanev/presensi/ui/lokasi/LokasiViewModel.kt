package com.adryanev.presensi.ui.lokasi

import android.content.Context
import androidx.lifecycle.*
import androidx.work.*
import com.adryanev.presensi.data.firestore.FirestoreRepository
import com.adryanev.presensi.data.firestore.LocationModel
import com.adryanev.presensi.utils.LocationHelper
import com.adryanev.presensi.utils.preference.PreferenceLokasi
import com.adryanev.presensi.utils.preference.PreferenceProfil
import com.adryanev.presensi.workmanager.SEND_LOCATION_WORKNAME
import com.adryanev.presensi.workmanager.SendLocationWorker
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.*
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber
import java.util.concurrent.TimeUnit

class LokasiViewModel(
    val firestoreRepository: FirestoreRepository,
    val lokasiPreference: PreferenceLokasi,
    val profilPreference: PreferenceProfil
) : ViewModel(), KoinComponent {

    private val _latitude = MutableLiveData<Double>()
    private val _longitude = MutableLiveData<Double>()
    private val _time = MutableLiveData<Long>()

    val latitude: LiveData<Double>
        get() = _latitude

    val longitude: LiveData<Double>
        get() = _longitude

    val time: LiveData<Long>
        get() = _time
    val context: Context by inject()

    val isOn = lokasiPreference.isOn

    private val lokasi = firestoreRepository.getLocationData(profilPreference.username!!)


    val mediatorLiveData = MediatorLiveData<LocationModel>()

    init {
        Timber.d("init LokasiViewModel")
        mediatorLiveData.addSource(lokasi) {

            val long = it.getDouble(LocationModel.LONGITUDE) as Double
            val lat = it.getDouble(LocationModel.LATITUDE) as Double
            val time = it.getLong(LocationModel.TIME) as Long

            mediatorLiveData.value = LocationModel(lat, long, time)
            _latitude.value = lat
            _longitude.value = long
            _time.value = time
        }

    }

    fun getLocation(): LiveData<LocationModel> {
        return mediatorLiveData
    }

    private suspend fun getUserLocation(): LocationModel {
        Timber.d("Get Current User location")

        val location = LocationHelper.getLocation()
        val time = System.currentTimeMillis()
        Timber.d("Creating Current User Location Model")
        return LocationModel(location.latitude, location.longitude, time)
    }

    private suspend fun insertDataToDb() {
        val location = getUserLocation()
        Timber.d("Inserting Current user location to database")
        firestoreRepository.insertLokasi(location)
    }

    fun turnOnLocation() {
        Timber.d("Turning on Location")
        viewModelScope.launch {
            insertDataToDb()

        }

        setLokasiPreference(true)

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

    }

    private fun setLokasiPreference(boolean: Boolean) {
        lokasiPreference.on = boolean
    }

    fun turnOffLocation() {
        Timber.d("Turning off Location")
        setLokasiPreference(false)
        val status = WorkManager.getInstance(context).cancelUniqueWork(
            SEND_LOCATION_WORKNAME
        )

    }


}
