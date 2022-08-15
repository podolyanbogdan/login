package com.example.login.bindingAdapter

import android.widget.AutoCompleteTextView
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.login.ui.main.MainScreen
import com.example.login.ui.main.adapter.ViewPagerAdapter
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


@BindingAdapter("setAdapter")
fun bindViewPagerAdapter(view: ViewPager2, activity: MainScreen) {
    val adapter = ViewPagerAdapter(activity.supportFragmentManager, activity.lifecycle)
    view.adapter = adapter
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
