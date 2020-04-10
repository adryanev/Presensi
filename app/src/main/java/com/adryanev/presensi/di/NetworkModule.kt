package com.adryanev.presensi.di

import com.adryanev.presensi.BuildConfig
import com.adryanev.presensi.data.networks.PremenagApi
import com.adryanev.presensi.utils.api.ResponseHandler
import com.adryanev.presensi.utils.preference.PreferenceProfil
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    factory { provideOkHttpClient() }
    single { provideRetrofit(get())}
    factory { providePremenagApi(get()) }
    factory { ResponseHandler() }
}


fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient()
        .newBuilder()
        .addNetworkInterceptor(StethoInterceptor())
        .addInterceptor {
            val token = PreferenceProfil.accessToken;
            val newRequest = it.request().newBuilder().addHeader("Authorization", "Bearer $token").build()
            it.proceed(newRequest) }
        .build()
}

fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_API)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}

fun providePremenagApi(retrofit: Retrofit): PremenagApi = retrofit.create(
    PremenagApi::class.java)