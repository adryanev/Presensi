package com.adryanev.presensi

import android.app.Application
import com.adryanev.presensi.di.appModule
import com.adryanev.presensi.di.firebaseModule
import com.adryanev.presensi.di.networkModule
import com.adryanev.presensi.di.viewModelModule
import com.birjuvachhani.locus.Locus
import com.facebook.stetho.Stetho
import com.google.android.gms.location.LocationRequest
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class Presensi : Application() {

    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)
        Timber.plant(Timber.DebugTree())
        startKoin {
            androidLogger()
            androidContext(this@Presensi)
            modules(listOf(appModule, viewModelModule, networkModule, firebaseModule))
        }
        Locus.configure {
            enableBackgroundUpdates = true
            forceBackgroundUpdates = true
            request {
                fastestInterval = 5000
                priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                interval = 10000
                maxWaitTime = 15000
            }
        }

    }
}