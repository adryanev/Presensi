package com.adryanev.presensi.bindings

import android.net.Uri
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.adryanev.presensi.BuildConfig
import com.adryanev.presensi.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import timber.log.Timber

@BindingAdapter("profile")
fun bindProfile(imageView: ImageView, url:String){
    Glide.with(imageView.context)
        .load(BuildConfig.BASE_URL+"media/users/$url")
        .apply(RequestOptions.circleCropTransform())
        .placeholder(R.drawable.logo_instansi)
        .into(imageView)
}

@BindingAdapter("epochToHuman")
fun bindEpochToHuman(textView:TextView, time: Long){
    val formatter =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    val formatted = Instant.ofEpochMilli(time).atZone(ZoneId.of("GMT")).format(formatter)
        val text = textView.context.getString(R.string.lokasi_terakhir_diakses,formatted)
        textView.text = text
}
