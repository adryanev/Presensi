package com.adryanev.presensi.data.networks.response


import com.adryanev.presensi.data.models.Pegawai
import com.adryanev.presensi.data.models.User
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginData(
    @Json(name = "pegawai")
    var pegawai: Pegawai? = null,
    @Json(name = "user")
    var user: User? = null
)