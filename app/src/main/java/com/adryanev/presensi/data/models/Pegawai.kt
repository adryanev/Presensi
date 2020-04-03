package com.adryanev.presensi.data.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Pegawai(
    @Json(name = "avatar")
    var avatar: String? = null,
    @Json(name = "created_at")
    var createdAt: Int? = null,
    @Json(name = "id")
    var id: Int = 0,
    @Json(name = "id_golongan")
    var idGolongan: Int = 0,
    @Json(name = "id_user")
    var idUser: Int = 0,
    @Json(name = "jabatan")
    var jabatan: String? = null,
    @Json(name = "nama")
    var nama: String? = null,
    @Json(name = "nip")
    var nip: String? = null,
    @Json(name = "updated_at")
    var updatedAt: Int? = null
)