package com.example.login.ui.notesList

import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.arch.ext.navigate
import com.example.login.databinding.RecNoteItemBinding
import com.example.login.repository.NoteRepository
import com.example.login.room.NoteDao
import com.example.login.room.NoteModel
import org.jetbrains.anko.bundleOf
import org.jetbrains.anko.doAsync


class NoteAdapter(
    private var notes: MutableList<NoteModel>,
    private val application: Application,
    private val fragment: Fragment
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
        editItem(holder)
        expanded(holder, position)

    }

    override fun getItemCount() = notes.size

    private fun expanded(holder: NoteViewHolder, position: Int){
        val isExpandable : Boolean = notes[position].isExpanded
        val arrowBtn = holder.recNoteItemBinding.collapseAndExpand
        val layout = holder.recNoteItemBinding.constExpandable

        layout.visibility = if(isExpandable) View.GONE else View.VISIBLE
        if(isExpandable){
            arrowBtn.setImageResource(R.drawable.ic_arrow_up)
        } else {
            arrowBtn.setImageResource(R.drawable.ic_arrow_down)
        }
        arrowBtn.setOnClickListener {
            val note = notes[position]
            note.isExpanded = !note.isExpanded
            notifyItemChanged(position)
        }
    }

    fun deleteItem(position: Int, holder: NoteViewHolder) {
        notes.removeAt(position)
        doAsync {
            holder.recNoteItemBinding.data?.id?.let { NoteRepository(application).deleteById(it) }
        }
        notifyItemRemoved(position)
        notifyDataSetChanged()
    }


    private fun editItem(holder: NoteViewHolder) {
        val params = holder.recNoteItemBinding.data?.id
        val bundle = bundleOf("title" to params)
        holder.recNoteItemBinding.imgEdit.setOnClickListener {
            fragment.navigate(R.id.editNoteFragment, bundle)
        }
    }

}
