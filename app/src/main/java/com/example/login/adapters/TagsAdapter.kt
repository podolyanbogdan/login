package com.example.login.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.data.DaysModel
import com.example.login.data.TagsModel
import com.example.login.databinding.RecTagItemBinding
import com.example.login.databinding.RecWeekItemBinding

class TagsAdapter(
    private val tags: List<TagsModel>
): RecyclerView.Adapter<TagsAdapter.TagsViewHolder>() {

    inner class TagsViewHolder(
        val recTagsItemBinding: RecTagItemBinding
    ) : RecyclerView.ViewHolder(recTagsItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TagsViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.rec_tag_item,
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: TagsViewHolder, position: Int) {
        holder.recTagsItemBinding.data = tags[position]
    }

    override fun getItemCount() = tags.size
}