package com.adryanev.presensi.di


import com.adryanev.presensi.data.firebase.FirebaseRepository
import com.adryanev.presensi.data.networks.repositories.LoginRepository
import com.adryanev.presensi.data.networks.repositories.RemoteRepository
import com.adryanev.presensi.data.networks.repositories.Repository
import com.adryanev.presensi.utils.preference.PreferenceProfil
import org.koin.dsl.module

val appModule = module {
    factory { LoginRepository(get()) }
    single { PreferenceProfil }
    factory { FirebaseRepository(get(), get()) }
}