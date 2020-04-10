package com.adryanev.presensi.data.networks.response

import com.squareup.moshi.Json

abstract class BaseApiResponse {
    @Json(name ="status") var status: Int = 0
    @Json(name="message") var message: String? = null
}