package com.adryanev.presensi.data.networks

import com.adryanev.presensi.data.networks.response.LoginResponse
import com.adryanev.presensi.utils.api.Resource
import com.adryanev.presensi.utils.api.ResponseHandler

class RemoteRepository(val premenagApi: PremenagApi, val responseHandler: ResponseHandler):Repository {
    override suspend fun login(username: String, password: String): Resource<LoginResponse> {

        return try {
            val response = premenagApi.login(username,password)
            return responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }
}