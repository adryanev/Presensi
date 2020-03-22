package com.adryanev.presensi.di

import android.content.Context
import com.adryanev.presensi.data.preference.Preference_UserProfile
import org.koin.dsl.module

val preferenceModule  = module {
    single { providePreferenceManager(get()) }
}

fun providePreferenceManager(context: Context): Preference_UserProfile{
    return  Preference_UserProfile.getInstance(context)
}