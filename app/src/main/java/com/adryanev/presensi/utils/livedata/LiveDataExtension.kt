package com.adryanev.presensi.utils.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.liveData
import com.adryanev.presensi.utils.api.ResponseResult
import kotlinx.coroutines.Dispatchers

inline fun <T> liveResponse(crossinline body: suspend ()->ResponseResult<T>) = liveData(Dispatchers.IO) {
    emit(ResponseResult.Loading)
    val result = body()
    emit(result)
    emit(ResponseResult.Complete)
}

fun <T, K, R> LiveData<T>.combineWith(
    liveData: LiveData<K>,
    block: (T?, K?) -> R
): LiveData<R> {
    val result = MediatorLiveData<R>()
    result.addSource(this) {
        result.value = block.invoke(this.value, liveData.value)
    }
    result.addSource(liveData) {
        result.value = block.invoke(this.value, liveData.value)
    }
    return result
}