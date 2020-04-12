package com.adryanev.presensi.di


import com.adryanev.presensi.data.firestore.FirestoreRepository
import com.adryanev.presensi.data.networks.repositories.LoginRepository
import com.adryanev.presensi.utils.preference.PreferenceLokasi
import com.adryanev.presensi.utils.preference.PreferenceProfil
import org.koin.dsl.module

val appModule = module {
    factory { LoginRepository(get()) }
    single { PreferenceProfil }
    factory { FirestoreRepository(get(), get()) }
    single { PreferenceLokasi }
}