package com.example.login.ui.addNote

import android.app.Application
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.data.ColorModel
import com.example.login.databinding.FragmentAddNoteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class AddNoteFragment : BaseFragment<FragmentAddNoteBinding>(R.layout.fragment_add_note),
    MenuProvider {
    override val viewModel: AddNoteViewModel by viewModel()


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
        menuInflater.inflate(R.menu.save, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.saveMenu -> {
                viewModel.createNote()
            }
        }
        return false
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
                    ColorAdapter(viewModel.colors.value as MutableList<ColorModel>, Application(),)
            }
        }
    }

}