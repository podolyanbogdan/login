package com.example.login.dataAdapter

import android.graphics.Bitmap
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat

@BindingAdapter("imageBitmap")
fun loadImage(iv: ImageView, bitmap: Bitmap?) {
    iv.setImageBitmap(bitmap)
}

@BindingAdapter("filter")
fun applyFilter(imageView: ImageView, value: ColorMatrix) {
    imageView.colorFilter = null
    val cf = ColorMatrixColorFilter(value)
    imageView.colorFilter = cf
}

@BindingAdapter("android:srcc")
fun setImage(imageView: ImageView, resourceId: Int) {
    val drawable: Drawable? =
        VectorDrawableCompat.create(imageView.resources, resourceId, imageView.context.theme)
    imageView.setImageDrawable(drawable)
}
