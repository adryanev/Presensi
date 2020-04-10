package com.adryanev.presensi.data.networks.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponse(
    @Json(name = "data")
    var `data`: LoginData? = null
):BaseApiResponse()