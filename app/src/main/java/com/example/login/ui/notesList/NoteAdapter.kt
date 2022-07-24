package com.example.login.ui.notesList

import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.databinding.RecNoteItemBinding
import com.example.login.repository.NoteRepository
import com.example.login.room.NoteDao
import com.example.login.room.NoteModel
import org.jetbrains.anko.doAsync


class NoteAdapter(
    private var notes: MutableList<NoteModel>,
    private val application: Application
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(
        val recNoteItemBinding: RecNoteItemBinding
    ) : RecyclerView.ViewHolder(recNoteItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NoteViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.rec_note_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.recNoteItemBinding.data = notes[position]

    }

    override fun getItemCount() = notes.size

    fun deleteItem(position: Int, holder: NoteViewHolder){
        notes.removeAt(position)
        doAsync {
            holder.recNoteItemBinding.data?.id?.let { NoteRepository(application).deleteById(it) }
        }
        notifyItemRemoved(position)
        notifyDataSetChanged()
    }



}