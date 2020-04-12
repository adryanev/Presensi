package com.adryanev.presensi.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.koin.dsl.module

val firebaseModule = module {
    single { provideFirebaseFirestore() }
}

fun provideFirebaseFirestore(): FirebaseFirestore {
    return Firebase.firestore
}