package com.adryanev.presensi.di


import com.adryanev.presensi.data.networks.RemoteRepository
import com.adryanev.presensi.data.networks.Repository
import com.adryanev.presensi.utils.preference.PreferenceProfil
import org.koin.dsl.module

val appModule = module {
    single<Repository> { RemoteRepository(get(), get()) }
    factory { PreferenceProfil }
}