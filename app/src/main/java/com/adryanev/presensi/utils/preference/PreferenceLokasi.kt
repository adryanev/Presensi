package com.adryanev.presensi.utils.preference

import com.chibatching.kotpref.KotprefModel
import com.chibatching.kotpref.livedata.asLiveData

object PreferenceLokasi : KotprefModel(){
    var on by booleanPref()
    var isOn = PreferenceLokasi.asLiveData(PreferenceLokasi::on)
}