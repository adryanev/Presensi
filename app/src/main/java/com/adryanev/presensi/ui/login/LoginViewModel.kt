package com.adryanev.presensi.ui.login

import android.util.Log
import androidx.lifecycle.*
import com.adryanev.presensi.data.models.login.LoginUiModel
import com.adryanev.presensi.data.networks.repositories.LoginRepository
import com.adryanev.presensi.data.networks.repositories.Repository
import com.adryanev.presensi.data.networks.response.LoginResponse
import com.adryanev.presensi.utils.api.ResponseResult
import com.adryanev.presensi.utils.livedata.SingleLiveEvent
import com.adryanev.presensi.utils.livedata.combineWith
import com.adryanev.presensi.utils.livedata.liveResponse
import com.adryanev.presensi.utils.preference.PreferenceProfil
import com.adryanev.presensi.utils.string.ResourceString
import com.adryanev.presensi.utils.string.TextResourceString
import kotlinx.coroutines.launch
import timber.log.Timber

class LoginViewModel(val loginRepository: LoginRepository, val preferenceProfil: PreferenceProfil) :
    ViewModel() {

    val toastMessage = SingleLiveEvent<ResourceString>()

    // << Data Binding Field Start >>
    var usernameField = MutableLiveData<String>()
    var passwordField = MutableLiveData<String>()
    // << Data Binding Field End >>


    private var _loginControl =  MutableLiveData<ResponseResult<LoginResponse>>()
    val loginSuccessControl: LiveData<ResponseResult<LoginResponse>>
        get() = _loginControl

    val uiModel: LoginUiModel by lazy {
        LoginUiModel()
    }

    val mediatorLiveData = MediatorLiveData<ResponseResult<LoginResponse>>()

    fun buttonClick() {
        val userUsername = usernameField.value.toString()
        val userPassword = passwordField.value.toString()

        Timber.d("Login Button clicked")
        if (!userUsername.isBlank() && !userPassword.isBlank()) {
            when {
                userUsername.isEmpty() -> toastMessage.value =
                    TextResourceString("Username Tidak Boleh Kosong")
                userPassword.isEmpty() -> toastMessage.value =
                    TextResourceString("Password tidak boleh Kosong")
                else -> {
                    viewModelScope.launch {
                        Timber.d("Coroutine Launched")
                        requestdata(userUsername, userPassword)

                    }
                }
            }
        }

    }

    private fun requestdata(userUsername: String, userPassword: String) {
        Timber.d("RequestData Method Called")
        mediatorLiveData.addSource(liveResponse { loginRepository.getLoginResponse(userUsername,userPassword) }) {

            when (it){
                is ResponseResult.Success -> {
                    uiModel.saveToPreference(PreferenceProfil,it.output.data!!)
                }
            }
            _loginControl.postValue(it)
        }


    }


}
