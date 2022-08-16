package com.example.login.recAdapter

import android.graphics.ColorMatrix
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.data.models.BWModel
import com.example.login.databinding.BwRecItemBinding

class BWAdapter(
    private var bwList: List<BWModel>,
    private val setMatrix: (ColorMatrix) -> Unit
) : RecyclerView.Adapter<BWAdapter.BWViewHolder>() {

    inner class BWViewHolder(
        val bwRecItemBinding: BwRecItemBinding
    ) : RecyclerView.ViewHolder(bwRecItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BWViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.bw_rec_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: BWViewHolder, position: Int) {
        holder.bwRecItemBinding.data = bwList[position]
        changeBW(holder)
    }

    override fun getItemCount() = bwList.size


    private fun changeBW(holder: BWViewHolder) {
        val item = holder.itemView
        val matrix = holder.bwRecItemBinding.data?.type
        item.setOnClickListener {
            if (matrix != null) {
                setMatrix(matrix)
            }
        }
    }
}