package com.adryanev.presensi

import android.app.Application
import com.adryanev.presensi.di.appModule
import com.adryanev.presensi.di.networkModule
import com.adryanev.presensi.di.preferenceModule
import com.adryanev.presensi.di.viewModelModule
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class Presensi: Application() {

    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)
        Timber.plant(Timber.DebugTree())
        startKoin {
            androidLogger()
            androidContext(this@Presensi)
            modules(listOf(appModule, viewModelModule, networkModule, preferenceModule))
        }

    }
}