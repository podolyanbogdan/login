package com.example.login.ui.notesList

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.databinding.FragmentNotesListBinding
import com.example.login.room.NoteModel
import com.example.login.utils.SwipeToDeleteCallback
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotesListFragment : BaseFragment<FragmentNotesListBinding>(R.layout.fragment_notes_list) {

    override val viewModel: NotesListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.viewmodel = viewModel
        initRecycler()
        return view
    }

    override fun setObservers() {
        super.setObservers()
        viewModel.fabAddNote.observe(this){
            navigate(R.id.addNoteFragment)
        }
    }

    private fun initRecycler() {
        viewModel.notes.observe(viewLifecycleOwner) {
            binding.rcNotes.also {
                it.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                it.adapter = NoteAdapter(viewModel.notes.value as MutableList<NoteModel>, Application())
                delete(it.adapter as NoteAdapter)
            }
        }
    }

    private fun delete(adapter: NoteAdapter)
    {
        val swipeGesture = object : SwipeToDeleteCallback(requireContext()){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                when(direction){
                    ItemTouchHelper.LEFT -> adapter.deleteItem(viewHolder.bindingAdapterPosition,
                        viewHolder as NoteAdapter.NoteViewHolder
                    )
                }
            }
        }
        val touchHelper = ItemTouchHelper(swipeGesture)
        touchHelper.attachToRecyclerView(binding.rcNotes)
    }


}