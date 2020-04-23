package com.adryanev.presensi.data.livedata

import android.os.Handler
import androidx.lifecycle.LiveData
import com.google.firebase.firestore.*
import timber.log.Timber

class FirestoreQueryLiveData(private val query:Query): LiveData<QuerySnapshot>(){

    private val listener = ValueListener()
    private var listenerRegistration: ListenerRegistration? = null

    private var listenerRemovePending = false
    private val handler = Handler()

    private val removeListener = Runnable {
        listenerRegistration!!.remove()
        listenerRemovePending = false
    }

    override fun onActive() {
        super.onActive()
        Timber.d("FirestoreQuery Active")
        if(listenerRemovePending){
            handler.removeCallbacks(removeListener)
        }
        else {
            listenerRegistration = query.addSnapshotListener(listener)
        }
        listenerRemovePending = false
    }

    override fun onInactive() {
        super.onInactive()
        Timber.d("FirestoreQuery Inactive")
        handler.postDelayed(removeListener, 2000)
        listenerRemovePending = true
    }



    private inner class ValueListener : EventListener<QuerySnapshot>{
        override fun onEvent(querySnapshot: QuerySnapshot?, e: FirebaseFirestoreException?) {
            if (e !=null){
                Timber.e(e,"Can't listen to querySnapshot: %s", querySnapshot)
                return
            }
            value = querySnapshot
        }
    }
}