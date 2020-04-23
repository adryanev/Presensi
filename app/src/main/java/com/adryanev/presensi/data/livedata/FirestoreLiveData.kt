package com.adryanev.presensi.data.livedata

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.adryanev.presensi.data.firestore.LocationModel
import com.google.firebase.firestore.*
import com.google.protobuf.DoubleValue
import timber.log.Timber

class FirestoreLiveData(val document: DocumentReference) : LiveData<DocumentSnapshot>() {
    private val listener = MySnapshotListener()

    private var listenerRegistration: ListenerRegistration? = null
    private var listenerRemovePending = false
    private val handler = Handler()

    private val removeListener = Runnable {
        listenerRegistration!!.remove()
        listenerRemovePending = false
    }

    override fun onActive() {
        super.onActive()
        Timber.d("FirestoreLiveData Active")
        if(listenerRemovePending){
            handler.removeCallbacks(removeListener)
        }
        else{
            listenerRegistration = document.addSnapshotListener(listener)

        }
        listenerRemovePending = false
    }

    override fun onInactive() {
        super.onInactive()
        Timber.d("FirestoreLiveData Inactive")
        handler.postDelayed(removeListener,2000)
        listenerRemovePending = true
    }

    inner class MySnapshotListener : EventListener<DocumentSnapshot>{
        override fun onEvent(snapshot: DocumentSnapshot?, e: FirebaseFirestoreException?) {

            if (e != null) {
                Timber.w(e)
                return
            }
            if (snapshot != null && snapshot.exists()) {
                Timber.d("Current data: ${snapshot.data}")
            } else {
                Timber.d("Current data: null")
            }
            value =snapshot
        }

    }

}