package com.adryanev.presensi.ui.checkin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CheckinViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is checkin Fragment"
    }
    val text: LiveData<String> = _text
}