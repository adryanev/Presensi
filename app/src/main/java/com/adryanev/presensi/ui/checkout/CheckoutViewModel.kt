package com.adryanev.presensi.ui.checkout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CheckoutViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is checkout Fragment"
    }
    val text: LiveData<String> = _text
}