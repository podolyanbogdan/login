package com.example.login.ui.screens.birdsList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.login.data.localeModels.BirdModelLocale
import com.example.login.data.models.BirdModel
import com.example.login.databinding.BirdRecItemBinding


class BirdsAdapter(
    private val recyclerActions: RecyclerActions
) :
    ListAdapter<BirdModelLocale, BirdsAdapter.BirdHolder>(DiffCallback()) {

    override fun onBindViewHolder(holder: BirdHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, recyclerActions)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BirdHolder {
        return BirdHolder.from(parent)
    }

    class BirdHolder private constructor(private val binding: BirdRecItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BirdModelLocale, recyclerActions: RecyclerActions) {
            binding.tvGenus.text = item.gen
            binding.tvPlace.text = item.cnt

            binding.button2.setOnClickListener {
                recyclerActions.showMore(item)
            }
        }

        companion object {
            fun from(parent: ViewGroup): BirdHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = BirdRecItemBinding.inflate(layoutInflater, parent, false)
                return BirdHolder(binding)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<BirdModelLocale>() {
        override fun areItemsTheSame(oldItem: BirdModelLocale, newItem: BirdModelLocale): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BirdModelLocale, newItem: BirdModelLocale): Boolean {
            return oldItem == newItem
        }
    }
}
