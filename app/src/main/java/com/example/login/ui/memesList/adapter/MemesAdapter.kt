package com.example.login.ui.memesList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.login.data.models.apiModels.MemeAPI
import com.example.login.data.models.localeModels.MemeLocale
import com.example.login.databinding.MemesItemRecBinding


class MemesAdapter() :
    PagingDataAdapter<MemeLocale, MemesAdapter.MemesHolder>(DiffCallback()) {

    override fun onBindViewHolder(holder: MemesHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemesHolder {
        return MemesHolder.from(parent)
    }


    class MemesHolder private constructor(private val binding: MemesItemRecBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MemeLocale) {
            binding.memes = item
        }

        companion object {
            fun from(parent: ViewGroup): MemesHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MemesItemRecBinding.inflate(layoutInflater, parent, false)
                return MemesHolder(binding)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<MemeLocale>() {
        override fun areItemsTheSame(oldItem: MemeLocale, newItem: MemeLocale): Boolean {
            return oldItem.iD == newItem.iD
        }

        override fun areContentsTheSame(oldItem: MemeLocale, newItem: MemeLocale): Boolean {
            return oldItem == newItem
        }
    }
}