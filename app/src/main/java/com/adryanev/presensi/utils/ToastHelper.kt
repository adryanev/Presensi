package com.adryanev.presensi.utils

sealed class ToastHelper{

    object Success : ToastHelper()
    object Warning: ToastHelper()
    object Info: ToastHelper()
    object Error: ToastHelper()

}