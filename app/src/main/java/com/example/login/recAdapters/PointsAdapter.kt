package com.example.login.recAdapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.data.PointsModel
import com.example.login.databinding.PointItemRecBinding

class PointsAdapter(
    private val points: List<PointsModel>
): RecyclerView.Adapter<PointsAdapter.PointsViewHolder>(){

    inner class PointsViewHolder(
        val recyclerPointItemRecBinding: PointItemRecBinding
    ) : RecyclerView.ViewHolder(recyclerPointItemRecBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PointsViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.point_item_rec,
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: PointsViewHolder, position: Int) {
        holder.recyclerPointItemRecBinding.data = points[position]
    }

    override fun getItemCount() = points.size

}