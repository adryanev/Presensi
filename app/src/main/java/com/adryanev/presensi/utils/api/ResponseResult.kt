package com.adryanev.presensi.utils.api

import java.lang.Exception

sealed class ResponseResult<out H> {
    data class Success<out T>(val output: T): ResponseResult<T>()
    data class Error (val exception: Exception): ResponseResult<Nothing>()
    object Loading : ResponseResult<Nothing>()
    object Complete: ResponseResult<Nothing>()
}