package com.adryanev.presensi.data.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "access_token")
    var accessToken: String? = null,
    @Json(name = "created_at")
    var createdAt: Int? = null,
    @Json(name = "email")
    var email: String? = null,
    @Json(name = "group")
    var group: String? = null,
    @Json(name = "id")
    var id: Int? = null,
    @Json(name = "status")
    var status: Int? = null,
    @Json(name = "updated_at")
    var updatedAt: Int? = null,
    @Json(name = "username")
    var username: String? = null
)