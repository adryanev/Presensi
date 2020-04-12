package com.adryanev.presensi.di

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.koin.dsl.module

val firebaseModule = module {
    factory { provideDatabaseReference() }
}

fun provideDatabaseReference(): DatabaseReference{
    return Firebase.database.reference
}