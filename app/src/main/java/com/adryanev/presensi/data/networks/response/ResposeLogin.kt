package com.adryanev.presensi.data.networks.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResposeLogin(
    @Json(name = "data")
    var `data`: DataLogin? = null,
    @Json(name = "message")
    var message: String? = null,
    @Json(name = "status")
    var status: Boolean? = null
)