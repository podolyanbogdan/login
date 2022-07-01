package com.example.login.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.data.WordTranslatedModel
import com.example.login.databinding.RecyclerviewWordBinding
import com.example.login.room.Word

class HistoryAdapter(
    private var words: List<Word>
) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    inner class HistoryViewHolder(
        val recyclerviewWordBinding: RecyclerviewWordBinding
    ) : RecyclerView.ViewHolder(recyclerviewWordBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HistoryViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.recyclerview_word,
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.recyclerviewWordBinding.data = words[position]
    }

    override fun getItemCount() = words.size

}