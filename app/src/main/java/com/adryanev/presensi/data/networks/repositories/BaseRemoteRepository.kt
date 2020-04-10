package com.adryanev.presensi.data.networks.repositories

import com.adryanev.presensi.data.networks.response.LoginResponse
import com.adryanev.presensi.utils.api.ResponseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

open class BaseRemoteRepository {

    suspend fun <T> safeApiCall( call: suspend() -> T): T?{
        return withContext(Dispatchers.IO){
            val result = callApi(call)
            var output: T? = null
            when(result){
                is ResponseResult.Success -> { output = result.output}
                is ResponseResult.Error -> { Timber.e ("Error: ${result.exception.message}")
                }
            }
            output
        }
    }

    suspend inline fun<T> callApi(crossinline call: suspend ()-> T): ResponseResult<T>{
        return try{
            val result = withContext(Dispatchers.IO){
                call()
            }
            ResponseResult.Success(result)

        } catch (e: Exception){
            ResponseResult.Error(e)
        }
    }

}