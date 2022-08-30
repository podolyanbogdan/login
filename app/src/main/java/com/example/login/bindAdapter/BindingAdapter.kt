package com.example.login.bindAdapter

import android.net.Uri
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.example.login.R
import com.example.login.data.constants.Constants

@BindingAdapter("imageUrl")
fun setImageUrl(imgView: ImageView, imgUrl: String?){
    val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
    val imgUri: Uri = when (imgUrl) {
        "01d" -> Constants.SUN.toUri().buildUpon().build()
        "04d" -> Constants.CLOUD.toUri().buildUpon().build()
        else -> Constants.NOT_FOUND.toUri().buildUpon().build()
    }
    imgUrl?.let {
        Glide.with(imgView.context)
            .asBitmap()
            .centerCrop()
            .error(R.drawable.img_not_found)
            .transition(BitmapTransitionOptions.withCrossFade(factory))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .load(imgUri)
            .into(imgView)
    }
}