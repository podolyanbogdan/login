package com.example.login.bindingAdapter

import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.example.login.data.enumss.EmptyState
import com.example.login.data.enumss.GifState
import com.google.android.material.tabs.TabLayout


@BindingAdapter("imageUrl")
fun setImageUrl(imgView: ImageView, imgUrl: String?){
    imgUrl?.let {
        val imgUri = "https:${it.replace("//", "").toUri().buildUpon().build()}"
        Glide.with(imgView.context)
            .load(imgUri)
            .into(imgView)
    }
}

@BindingAdapter("android:visibilityGif")
fun setVisibility(view: View, value: GifState) {
      when(value){
          GifState.SHOW_GIF ->  view.visibility = View.VISIBLE
          GifState.HIDE_GIF ->  view.visibility = View.INVISIBLE
      }
}

@BindingAdapter("android:visibilityEmpt")
fun setVisibilityTwo(view: View, value: EmptyState) {
    when(value){
        EmptyState.SHOW_STATE ->  view.visibility = View.VISIBLE
        EmptyState.HIDE_STATE ->  view.visibility = View.INVISIBLE
    }
}

@BindingAdapter("setPager")
fun bindViewPagerTabs(view: TabLayout, pagerView: ViewPager?) {
    view.setupWithViewPager(pagerView, true)
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
