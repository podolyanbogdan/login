package com.example.login.ui.addNote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.databinding.FragmentAddNoteBinding
import com.example.login.ui.notesList.NotesListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

import org.koin.androidx.viewmodel.ext.android.viewModel

class AddNoteFragment : BaseFragment<FragmentAddNoteBinding>(R.layout.fragment_add_note) {
    override val viewModel: AddNoteViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.viewmodel = viewModel
        return view
    }

    override fun setObservers() {
        super.setObservers()
        viewModel.createNoteTrigger.observe(this){
            navigate(R.id.nav_notes)
        }
    }

}