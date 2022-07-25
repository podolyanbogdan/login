package com.example.login.ui.addNote

import android.app.Application
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.data.ColorModel
import com.example.login.databinding.ColorPickerItemBinding
import com.example.login.repository.NoteRepository

class ColorAdapter(
    private var colors: MutableList<ColorModel>,
    private val application: Application
) : RecyclerView.Adapter<ColorAdapter.ColorViewHolder>() {

    inner class ColorViewHolder(
        val colorPickerItemBinding: ColorPickerItemBinding
    ) : RecyclerView.ViewHolder(colorPickerItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ColorViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.color_picker_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.colorPickerItemBinding.data = colors[position]
        NoteRepository(application).saveColor(R.color.black)
        getColor(holder)

    }

    override fun getItemCount() = colors.size

    private fun getColor(holder: ColorViewHolder) {
        holder.itemView.setOnClickListener {
            holder.colorPickerItemBinding.data?.bgcColor?.let { color ->
                NoteRepository(application).saveColor(color)
            }
        }
    }

}