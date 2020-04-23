package com.adryanev.presensi.data.firestore

import com.adryanev.presensi.data.livedata.FirestoreLiveData
import com.adryanev.presensi.data.livedata.FirestoreQueryLiveData
import com.adryanev.presensi.utils.preference.PreferenceProfil
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.*
import timber.log.Timber

class FirestoreRepository(val firestore: FirebaseFirestore, val prefernce: PreferenceProfil) {


    val lokasiReference = firestore.collection("lokasi")
    suspend fun insertLokasi(locationModel: LocationModel): Boolean{

        val data = hashMapOf(LocationModel.LATITUDE to locationModel.latitude,
        LocationModel.LONGITUDE to locationModel.longitude,
        LocationModel.TIME to locationModel.time)
    var success = false
        coroutineScope {
            launch {
                success = async {
                    lokasiReference.document(prefernce.username!!).set(data).addOnSuccessListener {
                        Timber.d("Success Insert Data")
                    }.addOnFailureListener { it ->
                        Timber.e(it)
                    }
                }.await().isSuccessful
            }
        }



        return success
    }

    fun getLocationData(username: String): FirestoreLiveData{
        return FirestoreLiveData(lokasiReference.document(username))

    }

    fun getLocationQueryLiveData(username: String): FirestoreQueryLiveData{
        return FirestoreQueryLiveData(lokasiReference)
    }

}