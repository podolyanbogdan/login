package com.example.login.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.constants.Constants
import com.example.login.data.DaysModel
import com.example.login.databinding.RecWeekItemBinding

class DaysAdapter(
    private val days: MutableList<DaysModel>,
    private val context: Context
) : RecyclerView.Adapter<DaysAdapter.DaysViewHolder>() {
    private lateinit var bgc: ConstraintLayout

    inner class DaysViewHolder(
        val recWeekItemBinding: RecWeekItemBinding,
    ) : RecyclerView.ViewHolder(recWeekItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DaysViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.rec_week_item,
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: DaysViewHolder, position: Int) {
        holder.recWeekItemBinding.data = days[position]
        bgc = holder.itemView.findViewById(R.id.recConstrain)
        holder.itemView.setOnClickListener {
            Toast.makeText(context, "${holder.recWeekItemBinding.data}", Toast.LENGTH_SHORT).show()
            holder.recWeekItemBinding.recConstrain.setBackgroundColor(R.color.purple_700)
            notifyItemChanged(position, it)
        }
    }

    override fun getItemCount() = days.size

}