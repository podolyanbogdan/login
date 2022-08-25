package com.example.login.bindAdapter

import android.widget.AutoCompleteTextView
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.example.login.R

@BindingAdapter("imageUrl")
fun setImageUrl(imgView: ImageView, imgUrl: String?){
    val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().build()
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

@BindingAdapter("dropdown_text")
fun setDropDownText(view: AutoCompleteTextView, value: String) {
    if (!view.text.equals(value)) {
        view.setText(value, false)
    }
}

@InverseBindingAdapter(attribute = "dropdown_text")
fun getDropDownText(view: AutoCompleteTextView): String {
    return view.text.toString()
}

@BindingAdapter("dropdown_textAttrChanged")
fun setDropDownListener(view: AutoCompleteTextView, listener: InverseBindingListener) {
    view.setOnItemClickListener { _, _, _, _ -> listener.onChange() }
}

