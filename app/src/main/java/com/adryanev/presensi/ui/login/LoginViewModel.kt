package com.adryanev.presensi.ui.login

import android.view.View
import android.widget.Toast
import androidx.lifecycle.*
import com.adryanev.presensi.data.models.login.LoginUiModel
import com.adryanev.presensi.data.networks.Repository
import com.adryanev.presensi.data.networks.response.LoginResponse
import com.adryanev.presensi.utils.preference.PreferenceProfil
import kotlinx.coroutines.launch
import org.jetbrains.anko.design.snackbar
import timber.log.Timber

class LoginViewModel(val repository: Repository, val preferenceProfil: PreferenceProfil) :
    ViewModel() {

    var username = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    val uiModel: MutableLiveData<LoginUiModel> by lazy {
        MutableLiveData<LoginUiModel>()
    }
    val loginResponse: MutableLiveData<LoginResponse?> by lazy {
        MutableLiveData<LoginResponse?>()
    }


    fun buttonClick() {
        Timber.d("Login Button Clicked")
        viewModelScope.launch {
            loginUser()
        }
    }

    suspend fun loginUser() {
        val response = repository.login(username.value!!, password.value!!)
        loginResponse.value = response.data
        Timber.d(response.message)


    }

}
