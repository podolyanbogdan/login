package com.example.login.ui.addNote

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.data.ColorModel
import com.example.login.data.NoteColors
import com.example.login.databinding.ColorPickerItemBinding
import com.example.login.repository.NoteRepository

class ColorAdapter(
    private var colors: MutableList<ColorModel>,
    private val repository: NoteRepository,
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
        setColors(holder)
        repository.saveColor(R.color.black)
        getColor(holder)

    }

    private fun setColors(holder: ColorViewHolder) {
        when (holder.colorPickerItemBinding.data?.id) {
            NoteColors.Red.value -> holder.colorPickerItemBinding.data?.bgcColor = R.color.red
            NoteColors.Yellow.value -> holder.colorPickerItemBinding.data?.bgcColor = R.color.yellow
            NoteColors.Green.value -> holder.colorPickerItemBinding.data?.bgcColor = R.color.green
            NoteColors.Pink.value -> holder.colorPickerItemBinding.data?.bgcColor = R.color.pink
            NoteColors.Blue.value -> holder.colorPickerItemBinding.data?.bgcColor = R.color.blue
            NoteColors.LightBlue.value -> holder.colorPickerItemBinding.data?.bgcColor = R.color.light_blue
        }
    }

    override fun getItemCount() = colors.size

    private fun getColor(holder: ColorViewHolder) {
        val viewColor = holder.colorPickerItemBinding.textView2
        viewColor.setOnClickListener {
            holder.colorPickerItemBinding.data?.bgcColor?.let { color ->
                repository.saveColor(color)
            }
        }
    }


}