package com.adryanev.presensi.bindings

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.adryanev.presensi.BuildConfig
import com.adryanev.presensi.MainActivityViewModel
import com.adryanev.presensi.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.navigation.NavigationView

@BindingAdapter("profile")
fun bindProfile(imageView: ImageView, url:String){
    Glide.with(imageView.context)
        .load(BuildConfig.BASE_URL+"media/users/$url")
        .apply(RequestOptions.circleCropTransform())
        .placeholder(R.drawable.logo_instansi)
        .into(imageView)
}
