package com.example.login.ui.screens.birdsList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.data.models.BirdModel
import com.example.login.databinding.BirdRecItemBinding


class BirdsAdapter(
    private var birdsList: List<BirdModel>,
    private var showMore: (BirdModel) -> Unit
) : RecyclerView.Adapter<BirdsAdapter.BirdsViewHolder>() {

    inner class BirdsViewHolder(
        val birdRecItemBinding: BirdRecItemBinding
    ) : RecyclerView.ViewHolder(birdRecItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BirdsViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.bird_rec_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: BirdsViewHolder, position: Int) {
        holder.birdRecItemBinding.data = birdsList[position]
        showMore(holder)
    }

    override fun getItemCount() = birdsList.size

    private fun showMore(holder: BirdsViewHolder){
        val button = holder.birdRecItemBinding.button2
        val detailValue = holder.birdRecItemBinding.data
        button.setOnClickListener {
            if (detailValue != null) {
                showMore(detailValue)
            }
        }
    }
}
