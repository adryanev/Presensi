package com.adryanev.presensi.utils

import android.content.Context
import es.dmoral.toasty.Toasty

fun Context.toast(type: ToastHelper,message: String) {

    when (type){
        is ToastHelper.Success -> {
            Toasty.success(
                this,message,Toasty.LENGTH_SHORT,true
            ).show()
        }
        is ToastHelper.Info -> {
            Toasty.info(
                this,message,Toasty.LENGTH_SHORT,true
            ).show()
        }
        is ToastHelper.Warning -> {
            Toasty.warning(this,message,Toasty.LENGTH_SHORT,true).show()
        }
        is ToastHelper.Error -> {
            Toasty.error(this,message,Toasty.LENGTH_SHORT,true).show()
        }
    }


}
