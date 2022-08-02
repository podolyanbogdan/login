package com.example.login.ui.sensorList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.data.House
import com.example.login.databinding.SensorRecyItemBinding
import com.example.login.repository.SensorRepository

class SensorAdapter(
    private var houses: MutableList<House>,
    private var repo: SensorRepository
) : RecyclerView.Adapter<SensorAdapter.SensorViewHolder>() {

    inner class SensorViewHolder(
        val sensorRecyItemBinding: SensorRecyItemBinding
    ) : RecyclerView.ViewHolder(sensorRecyItemBinding.root)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SensorViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.sensor_recy_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: SensorViewHolder, position: Int) {
        holder.sensorRecyItemBinding.data = houses[position]
        deleteItem(position, holder)
    }

    override fun getItemCount() = houses.size

    private fun deleteItem(position: Int, holder: SensorViewHolder) {
        holder.sensorRecyItemBinding.imgDeleteItem.setOnClickListener {
            houses.removeAt(position)
            notifyItemRemoved(position)
            notifyDataSetChanged()
        }
    }

}