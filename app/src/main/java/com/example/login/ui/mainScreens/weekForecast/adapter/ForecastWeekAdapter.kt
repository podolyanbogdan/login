package com.example.login.ui.mainScreens.weekForecast.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.login.data.modelsAPI.MainInfo
import com.example.login.databinding.ForecastDayItemBinding
import com.example.login.utils.AppUtils.Companion.getDayName

class ForecastWeekAdapter() :
    ListAdapter<MainInfo, ForecastWeekAdapter.ForecastWeekHolder>(DiffCallback()) {

    override fun onBindViewHolder(holder: ForecastWeekHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastWeekHolder {
        return ForecastWeekHolder.from(parent)
    }

    class ForecastWeekHolder private constructor(private val binding: ForecastDayItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MainInfo) {
            binding.data = item
            binding.tvDaysName.text = getDayName()
        }

        companion object {
            fun from(parent: ViewGroup): ForecastWeekHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ForecastDayItemBinding.inflate(layoutInflater, parent, false)
                return ForecastWeekHolder(binding)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<MainInfo>() {
        override fun areItemsTheSame(oldItem: MainInfo, newItem: MainInfo): Boolean {
            return oldItem.dt == newItem.dt
        }

        override fun areContentsTheSame(oldItem: MainInfo, newItem: MainInfo): Boolean {
            return oldItem == newItem
        }
    }
}