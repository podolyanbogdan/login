package com.example.login.ui.mainScreens.cities.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.login.data.localeModels.DailyForecastLocale
import com.example.login.data.modelsAPI.DailyForecastAPI
import com.example.login.databinding.CityRecItemBinding
import com.example.login.utils.AppUtils.Companion.currentDay

class ForecastCitiesAdapter(
    private val recyclerClick: RecyclerClick
) :
    ListAdapter<DailyForecastLocale, ForecastCitiesAdapter.ForecastCitiesHolder>(DiffCallback()) {

    override fun onBindViewHolder(holder: ForecastCitiesHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, recyclerClick)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastCitiesHolder {
        return ForecastCitiesHolder.from(parent)
    }

    class ForecastCitiesHolder private constructor(private val binding: CityRecItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DailyForecastLocale, recyclerClick: RecyclerClick) {
            binding.city = item.cityLocale
            binding.leftData = item.list[currentDay()]
            binding.rightData = item.list[currentDay()+1]
            binding.container.setOnClickListener {
                recyclerClick.showCityInfo(item)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ForecastCitiesHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CityRecItemBinding.inflate(layoutInflater, parent, false)
                return ForecastCitiesHolder(binding)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<DailyForecastLocale>() {
        override fun areItemsTheSame(oldItem: DailyForecastLocale, newItem: DailyForecastLocale): Boolean {
            return oldItem.cityLocale.name == newItem.cityLocale.name
        }

        override fun areContentsTheSame(oldItem: DailyForecastLocale, newItem: DailyForecastLocale): Boolean {
            return oldItem == newItem
        }
    }
}