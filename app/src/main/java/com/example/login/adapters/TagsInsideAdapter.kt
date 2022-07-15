package com.example.login.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.data.TagsModel
import com.example.login.databinding.RecInsideTagItemBinding


class TagsInsideAdapter(
    private val tags: List<TagsModel>
) : RecyclerView.Adapter<TagsInsideAdapter.TagsInsideViewHolder>() {

    inner class TagsInsideViewHolder(
        val recInsideTagItemBinding: RecInsideTagItemBinding
    ) : RecyclerView.ViewHolder(recInsideTagItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TagsInsideViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.rec_inside_tag_item,
                parent,
                false
            )
        )
    

    override fun onBindViewHolder(holder: TagsInsideViewHolder, position: Int) {
        holder.recInsideTagItemBinding.data = tags[position]
    }

    override fun getItemCount() = tags.size
}