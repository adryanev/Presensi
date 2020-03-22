package com.adryanev.presensi.di

import com.adryanev.presensi.BuildConfig
import com.adryanev.presensi.data.networks.PremenagApi
import com.adryanev.presensi.utils.api.ResponseHandler
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    factory { provideOkHttpClient() }
    factory { providePremenagApi(get()) }
    single { provideRetrofit(get())}
    factory { ResponseHandler() }
}


fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient()
        .newBuilder()
        .addNetworkInterceptor(StethoInterceptor())
        .build()
}

fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}

fun providePremenagApi(retrofit: Retrofit): PremenagApi = retrofit.create(
    PremenagApi::class.java)