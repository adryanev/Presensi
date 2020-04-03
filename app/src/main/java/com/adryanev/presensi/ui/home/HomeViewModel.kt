package com.adryanev.presensi.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adryanev.presensi.utils.preference.PreferenceProfil
import com.adryanev.presensi.utils.preference.PreferenceProfil.login
import com.chibatching.kotpref.livedata.asLiveData

class HomeViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
    val isLogin: LiveData<Boolean>  = PreferenceProfil.isLogin


}