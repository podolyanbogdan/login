package com.example.login.recAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.data.UserModel
import com.example.login.databinding.PointItemRecBinding


class PointsAdapter(
    private val points: MutableList<UserModel>,
    private val context: Context
) : RecyclerView.Adapter<PointsAdapter.PointsViewHolder>() {
    private lateinit var deleteBtn: ImageView
    private lateinit var characterImage: ImageView


    inner class PointsViewHolder(
        val recyclerPointItemRecBinding: PointItemRecBinding,
    ) : RecyclerView.ViewHolder(recyclerPointItemRecBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PointsViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.point_item_rec,
                parent,
                false,
            )
        )

    override fun onBindViewHolder(holder: PointsViewHolder, position: Int) {
        holder.recyclerPointItemRecBinding.data = points[position]
        deleteBtn = holder.itemView.findViewById(R.id.imgDelete)
        characterImage = holder.itemView.findViewById(R.id.imgStar)

        deleteBtn.setOnClickListener {
            removeAt(position)
        }
    }

    override fun getItemCount() = points.size


    private fun removeAt(position: Int) {
        points.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, points.size)
    }
}