package com.adryanev.presensi.data.networks.repositories

import com.adryanev.presensi.data.networks.response.LoginResponse
import com.adryanev.presensi.utils.api.Resource

interface Repository {

   suspend fun login(username: String, password: String): Resource<LoginResponse>
}