package com.example.login.ui.mainScreens.weekForecast.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.login.data.constants.Constants.DAY_NAME_FORMATTER
import com.example.login.data.localeModels.MainInfoLocale
import com.example.login.data.modelsAPI.MainInfoAPI
import com.example.login.databinding.ForecastDayItemBinding
import com.example.login.utils.AppUtils
import com.example.login.utils.AppUtils.Companion.getDayName
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class ForecastWeekAdapter() :
    ListAdapter<MainInfoLocale, ForecastWeekAdapter.ForecastWeekHolder>(DiffCallback()) {

    override fun onBindViewHolder(holder: ForecastWeekHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastWeekHolder {
        return ForecastWeekHolder.from(parent)
    }

    class ForecastWeekHolder private constructor(private val binding: ForecastDayItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MainInfoLocale) {
            binding.data = item
            binding.tvDaysName.text = getDayName(item.dt.toLong())
        }

        companion object {
            fun from(parent: ViewGroup): ForecastWeekHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ForecastDayItemBinding.inflate(layoutInflater, parent, false)
                return ForecastWeekHolder(binding)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<MainInfoLocale>() {
        override fun areItemsTheSame(oldItem: MainInfoLocale, newItem: MainInfoLocale): Boolean {
            return oldItem.dt == newItem.dt
        }

        override fun areContentsTheSame(oldItem: MainInfoLocale, newItem: MainInfoLocale): Boolean {
            return oldItem == newItem
        }
    }
}