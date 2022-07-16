package com.example.login.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.data.DaysModel
import com.example.login.databinding.RecWeekItemBinding


class DaysAdapter(
    private val days: MutableList<DaysModel>,
) : RecyclerView.Adapter<DaysAdapter.DaysViewHolder>() {

    inner class DaysViewHolder(
        val recWeekItemBinding: RecWeekItemBinding,
    ) : RecyclerView.ViewHolder(recWeekItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DaysViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.rec_week_item,
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: DaysViewHolder, position: Int) {
        holder.recWeekItemBinding.data = days[position]

    }

    override fun getItemCount() = days.size
}