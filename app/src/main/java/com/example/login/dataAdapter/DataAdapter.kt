package com.example.login.dataAdapter

import android.graphics.Bitmap
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.databinding.BindingAdapter
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat

@BindingAdapter("app:imageBitmap")
fun setBitmapImage(iv: ImageView, bitmap: Bitmap?) {
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

@BindingAdapter(value = ["onValueChangeBrightness"])
fun setOnValueChangeBrightness(seekBar: SeekBar, listener: OnValueChangeBrightness) {
    seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
        override fun onProgressChanged(
            seekBar: SeekBar?,
            progress: Int,
            fromUser: Boolean
        ) {
           listener.onValueChangedBrightness(progress)
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {
        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {
        }
    })
}

@BindingAdapter(value = ["onValueChangeContrast"])
fun setOnValueChangeContrast(seekBar: SeekBar, listener: OnValueChangeContrast) {
    seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
        override fun onProgressChanged(
            seekBar: SeekBar?,
            progress: Int,
            fromUser: Boolean
        ) {
            listener.onValueChangedContrast(progress)
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {
        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {
        }
    })
}


interface OnValueChangeBrightness {
    fun onValueChangedBrightness(value: Int)
}

interface OnValueChangeContrast{
    fun onValueChangedContrast(value: Int)
}
