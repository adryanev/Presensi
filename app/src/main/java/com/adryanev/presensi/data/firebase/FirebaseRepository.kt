package com.adryanev.presensi.data.firebase

import com.adryanev.presensi.utils.preference.PreferenceProfil
import com.google.firebase.database.DatabaseReference

class FirebaseRepository(val databaseReference: DatabaseReference, val prefernce: PreferenceProfil) {


    fun insertLokasi(locationModel: LocationModel): Boolean{
        var success = false;
        databaseReference.child("lokasi").child(prefernce.username!!).setValue(locationModel).addOnSuccessListener {
            success = true
        }.addOnFailureListener {
            success = false
        }

        return success
    }

}