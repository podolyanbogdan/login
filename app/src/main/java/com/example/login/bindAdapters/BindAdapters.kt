package com.example.login.bindAdapters

import android.widget.AutoCompleteTextView
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.bumptech.glide.Glide


@BindingAdapter("imageUrl")
fun setImageUrl(imgView: ImageView, imgUrl: String?){
    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().build()
        Glide.with(imgView.context)
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