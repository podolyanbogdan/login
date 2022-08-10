package com.example.login.recAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.data.BWModel
import com.example.login.databinding.BwRecItemBinding
import com.example.login.ui.editor.CutViewModel

class BWAdapter(
    private var bwList: List<BWModel>,
    private var viewModel: CutViewModel
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
        itemClicked(holder)
    }

    override fun getItemCount() = bwList.size

    private fun itemClicked(holder: BWViewHolder){
        val item = holder.itemView
        item.setOnClickListener {
            viewModel.bwTypes.value = holder.bwRecItemBinding.data?.type
        }
    }
}