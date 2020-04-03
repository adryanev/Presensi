package com.adryanev.presensi.data.models.login

import com.adryanev.presensi.data.networks.response.LoginData
import com.adryanev.presensi.utils.preference.PreferenceProfil
import com.chibatching.kotpref.bulk
import java.util.regex.Pattern

data class LoginUiModel(var username:String? = null, var password:String? = null) {
    fun saveToPreference(preferenceProfil: PreferenceProfil, loginData: LoginData){
        preferenceProfil.bulk {
            login = true
            username = loginData.user?.username
            nama = loginData.pegawai?.nama
            nip = loginData.pegawai?.nip
            avatar = loginData.pegawai?.avatar
            golongan = loginData.pegawai?.idGolongan!!
            jabatan = loginData.pegawai?.jabatan
            accessToken = loginData.user?.accessToken
            group = loginData.user?.group


        }
    }

    fun isUsernameValid() : Boolean {
        return Pattern.matches("[0-9]",username!!);
    }
    fun isPasswordValid(): Boolean {
        return  password!!.length > 6
    }

}