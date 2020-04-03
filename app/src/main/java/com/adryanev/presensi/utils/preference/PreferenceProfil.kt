package com.adryanev.presensi.utils.preference

import com.chibatching.kotpref.KotprefModel
import com.chibatching.kotpref.livedata.asLiveData

object PreferenceProfil: KotprefModel() {

    var login by booleanPref()
    val isLogin = PreferenceProfil.asLiveData(PreferenceProfil::login)
    var username by nullableStringPref()
    var nama by nullableStringPref()
    var nip by nullableStringPref()
    var avatar by nullableStringPref()
    var golongan by intPref()
    var jabatan by nullableStringPref()
    var accessToken by nullableStringPref()
    var group by nullableStringPref()
    var email by nullableStringPref()
}