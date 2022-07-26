package com.example.login.ui.addNote

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.data.ColorModel
import com.example.login.databinding.FragmentAddNoteBinding
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
        val toolbar = requireActivity().findViewById<Toolbar>(R.id.toolbar)
        val menu = toolbar.findViewById<View>(R.id.sortMenu)
        menu.setBackgroundResource(R.drawable.ic_save_note)
        initRecycler()
        return view
    }

    override fun setObservers() {
        super.setObservers()
        viewModel.createNoteTrigger.observe(this) {
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