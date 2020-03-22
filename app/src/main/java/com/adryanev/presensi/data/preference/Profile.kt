package com.adryanev.presensi.data.preference

import com.skydoves.preferenceroom.KeyName
import com.skydoves.preferenceroom.PreferenceEntity

@PreferenceEntity("UserProfile")
open class Profile {
    @JvmField
    val isLogin: Boolean = false

    @KeyName("username")
    @JvmField
    val username: String? = null

    @KeyName("nama")
    @JvmField
    val nama: String? = null

    @KeyName("nip")
    @JvmField
    val nip: String? = null

    @KeyName("avatar")
    @JvmField
    val avatar: String? = null

    @KeyName("golongan")
    @JvmField
    val golongan: Int = 0

    @KeyName("jabatan")
    @JvmField
    val jabatan: String? = null

    @KeyName("accessToken")
    @JvmField
    val accessToken: String? = null

    @KeyName("group")
    @JvmField
    val group: String? = null

    @KeyName("email")
    @JvmField
    val email: String? = null


}