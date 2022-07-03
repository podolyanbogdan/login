package com.example.login.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.data.WordTranslatedModel
import com.example.login.databinding.RecyclerresultWordBinding

class ResultAdapter(
    private val words: List<WordTranslatedModel>
): RecyclerView.Adapter<ResultAdapter.ResultViewHolder>(){

    inner class ResultViewHolder(
        val recyclerresultWordBinding: RecyclerresultWordBinding
    ) : RecyclerView.ViewHolder(recyclerresultWordBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ResultViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.recyclerresult_word,
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        holder.recyclerresultWordBinding.data = words[position]
    }

    override fun getItemCount() = words.size

}


