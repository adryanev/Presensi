package com.adryanev.presensi.data.networks

import com.adryanev.presensi.data.networks.response.LoginResponse
import retrofit2.http.Field
import retrofit2.http.POST

interface PremenagApi {

    @POST("/auth/login")
    suspend fun login(@Field("username") username:String, @Field("password") password:String): LoginResponse
}