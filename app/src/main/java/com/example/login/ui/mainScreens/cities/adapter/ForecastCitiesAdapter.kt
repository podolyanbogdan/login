package com.example.login.ui.mainScreens.cities.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.login.data.modelsAPI.DailyForecastAPI
import com.example.login.data.modelsAPI.MainInfo
import com.example.login.databinding.CityRecItemBinding
import com.example.login.databinding.ForecastDayItemBinding
import com.example.login.utils.AppUtils.Companion.currentDay

class ForecastCitiesAdapter() :
    ListAdapter<DailyForecastAPI, ForecastCitiesAdapter.ForecastCitiesHolder>(DiffCallback()) {

    override fun onBindViewHolder(holder: ForecastCitiesHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastCitiesHolder {
        return ForecastCitiesHolder.from(parent)
    }

    class ForecastCitiesHolder private constructor(private val binding: CityRecItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DailyForecastAPI) {
            binding.city = item.city
            binding.leftData = item.list[currentDay()]
            binding.rightData = item.list[currentDay()+1]
        }

        companion object {
            fun from(parent: ViewGroup): ForecastCitiesHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CityRecItemBinding.inflate(layoutInflater, parent, false)
                return ForecastCitiesHolder(binding)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<DailyForecastAPI>() {
        override fun areItemsTheSame(oldItem: DailyForecastAPI, newItem: DailyForecastAPI): Boolean {
            return oldItem.cod == newItem.cod
        }

        override fun areContentsTheSame(oldItem: DailyForecastAPI, newItem: DailyForecastAPI): Boolean {
            return oldItem == newItem
        }
    }
}