package com.adryanev.presensi

import androidx.lifecycle.ViewModel
import com.adryanev.presensi.utils.preference.PreferenceProfil

class MainActivityViewModel (): ViewModel(){

    val preference:PreferenceProfil = PreferenceProfil

    fun logout (){
        preference.clear()
    }

}