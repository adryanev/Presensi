package com.adryanev.presensi.data.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "access_token")
    var accessToken: String? = null,
    @Json(name = "created_at")
    var createdAt: Int = 0,
    @Json(name = "email")
    var email: String? = null,
    @Json(name = "group")
    var group: String? = null,
    @Json(name = "id")
    var id: Int = 0,
    @Json(name = "status")
    var status: Int = 0,
    @Json(name = "updated_at")
    var updatedAt: Int = 0,
    @Json(name = "username")
    var username: String? = null
)