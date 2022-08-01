package com.example.login.ui.notesList

import android.app.Application
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.databinding.FragmentNotesListBinding
import com.example.login.repository.NoteRepository
import com.example.login.room.NoteModel
import com.example.login.room.SortName
import com.example.login.room.SortType
import com.example.login.utils.SwipeToDeleteCallback
import org.koin.androidx.viewmodel.ext.android.viewModel


class NotesListFragment : BaseFragment<FragmentNotesListBinding>(R.layout.fragment_notes_list), MenuProvider{
    override val viewModel: NotesListViewModel by viewModel()
    private var current = SortType.ASC

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.viewmodel = viewModel
        initRecycler()
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        return view
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.parent, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when(menuItem.itemId){
            R.id.byDate -> {
                current = when(current){
                    SortType.ASC -> {
                        menuItem.setIcon(R.drawable.ic_arrow_down)
                        viewModel.sortBy(SortName.Date, SortType.ASC)
                        SortType.DESC
                    }
                    SortType.DESC -> {
                        menuItem.setIcon(R.drawable.ic_arrow_up)
                        viewModel.sortBy(SortName.Date, SortType.DESC)
                        SortType.ASC
                    }
                }
            }
            R.id.byTitle -> {
                current = when(current){
                    SortType.ASC -> {
                        menuItem.setIcon(R.drawable.ic_arrow_down)
                        viewModel.sortBy(SortName.Title, SortType.ASC)
                        SortType.DESC
                    }
                    SortType.DESC -> {
                        menuItem.setIcon(R.drawable.ic_arrow_up)
                        viewModel.sortBy(SortName.Title, SortType.DESC)
                        SortType.ASC
                    }
                }
            }
            R.id.byColor -> {
                current = when(current){
                    SortType.ASC -> {
                        menuItem.setIcon(R.drawable.ic_arrow_down)
                        viewModel.sortBy(SortName.Color, SortType.ASC)
                        SortType.DESC
                    }
                    SortType.DESC -> {
                        menuItem.setIcon(R.drawable.ic_arrow_up)
                        viewModel.sortBy(SortName.Color, SortType.DESC)
                        SortType.ASC
                    }
                }
            }
            R.id.byContent -> {
                current = when(current){
                    SortType.ASC -> {
                        menuItem.setIcon(R.drawable.ic_arrow_down)
                        viewModel.sortBy(SortName.Content, SortType.ASC)
                        SortType.DESC
                    }
                    SortType.DESC -> {
                        menuItem.setIcon(R.drawable.ic_arrow_up)
                        viewModel.sortBy(SortName.Content, SortType.DESC)
                        SortType.ASC
                    }
                }
            }
            R.id.removeSort -> {
                viewModel.loadCurrencyList()
            }
        }
        return false
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
                it.adapter = parentFragment?.let { frag -> NoteAdapter(viewModel.notes.value as MutableList<NoteModel>, Application(), frag) }
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