package com.example.login.ui.editNote

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.data.ColorModel
import com.example.login.databinding.FragmentEditNoteBinding
import com.example.login.ui.addNote.AddNoteViewModel
import com.example.login.ui.addNote.ColorAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class EditNoteFragment : BaseFragment<FragmentEditNoteBinding>(R.layout.fragment_edit_note) {
    override val viewModel: EditNoteViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.viewmodel = viewModel
        val bundle = arguments?.getInt("title")
        viewModel.idToCheck.value = bundle
        initRecycler()
        return view
    }

    override fun setObservers() {
        super.setObservers()
        viewModel.editNoteTrigger.observe(this) {
            if (it) navigate(R.id.nav_notes)
            else showToast(getString(R.string.msg_if_empty))
        }
    }

    private fun initRecycler() {
        viewModel.colors.observe(viewLifecycleOwner) {
            binding.recColor.also {
                it.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                it.adapter =
                    ColorAdapter(viewModel.colors.value as MutableList<ColorModel>, Application())
            }
        }
    }

}