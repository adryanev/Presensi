package com.adryanev.presensi.data.networks.repositories

import com.adryanev.presensi.data.networks.PremenagApi
import com.adryanev.presensi.data.networks.response.LoginData
import com.adryanev.presensi.data.networks.response.LoginResponse
import com.adryanev.presensi.utils.api.ResponseResult

class LoginRepository(val premenagApi: PremenagApi) : BaseRemoteRepository() {

    suspend fun getLoginResponse(
        username: String,
        password: String
    )  = callApi(
            call = {
                premenagApi.login(username, password)
            }
        )

}


