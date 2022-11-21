package com.example.login.ui.screens.addTask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.addCallback
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.adapters.TagsAdapter
import com.example.login.adapters.TagsInsideAdapter
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.constants.Constants.dialogDate
import com.example.login.constants.Constants.dialogTag
import com.example.login.constants.Constants.dialogTime
import com.example.login.constants.Constants.fieldPopup
import com.example.login.constants.Constants.forceShowIcon
import com.example.login.data.TagsModel
import com.example.login.databinding.FragmentAddTaskBinding
import com.example.login.repository.TaskRepository
import com.example.login.repository.TaskRepository.getTagInside
import com.example.login.repository.TaskRepository.hide
import com.example.login.ui.datePicker.DatePickerDialog
import com.example.login.ui.dialogFragment.TagDialogFragment
import com.example.login.ui.timePicker.TimePickerDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel


class AddTaskFragment : BaseFragment<FragmentAddTaskBinding>(R.layout.fragment_add_task) {
    override val viewModel: AddTaskViewModel by viewModel()
    private lateinit var corrd: CoordinatorLayout
    private var tagName: String = ""
    private val tagsList = mutableListOf<TagsModel>()
    private var whichTime = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {}
        binding.viewmodel = viewModel
        return view
    }


    override fun setObservers() {
        super.setObservers()
        viewModel.backPressed.observe(this) {
            if (it) clickBack()
        }
        viewModel.takeDate.observe(this) {
            if (it) setDate()
        }
        viewModel.takeTimeFirst.observe(this) {
            if (it) {
                whichTime = 1
                openTimeDialog()
            }
        }
        viewModel.takeTimeSecond.observe(this) {
            if (it) {
                whichTime = 2
                openTimeDialog()
            }
        }
        viewModel.showTagDialog.observe(this) {
            if (it) openTagDialog()

        }
        viewModel.createTask.observe(this) {
            if (it) {
                createTask()
            }

        }
        viewModel.descSettings.observe(this) {
            if (it) showPopup()
        }
    }

    private fun showPopup() {
        val popupMenu = PopupMenu(requireContext(), binding.btnDesc)
        popupMenu.inflate(R.menu.desc_menu)
        popupMenu.show()
        try {
            val fieldMPopup = PopupMenu::class.java.getDeclaredField(fieldPopup)
            fieldMPopup.isAccessible = true
            val mPopup = fieldMPopup.get(popupMenu)
            mPopup.javaClass
                .getDeclaredMethod(forceShowIcon, Boolean::class.java)
                .invoke(mPopup, true)
        } finally {
            popupMenu.show()
        }
    }

    private fun openTagDialog() {
        TagDialogFragment(
            onSaveClickListener = { tag ->
                tagName = tag
                TaskRepository.getTag(tag)
                initRecycler()
            }
        ).show(parentFragmentManager, dialogTag)
    }

    private fun openTimeDialog() {
        TimePickerDialog(
            onSaveClickListener = { time ->
                if (whichTime == 1) viewModel.setTimeFirst.value = time
                if (whichTime == 2) viewModel.setTimeSecond.value = time
            }
        ).show(parentFragmentManager, dialogTime)
    }


    private fun initRecycler() {
        val tag = TagsModel(tagName)
        tagsList.add(tag)
        tag.randomColor()
        getTagInside(tagsList)
        viewModel.tags.value = tagsList
        viewModel.tags.observe(viewLifecycleOwner) {
            binding.recTags.also {
                it.layoutManager = GridLayoutManager(requireContext(), 3)
                it.adapter = TagsAdapter(tagsList)
            }
        }
    }

    private fun radioBtnValue(): String {
        val checkedRadioValue = binding.radioGroup.checkedRadioButtonId
        val result = requireActivity().findViewById<RadioButton>(checkedRadioValue)
        return result?.text.toString()
    }

    private fun setDate() {
        DatePickerDialog(
            onSaveClickListenerDate = { date ->
                viewModel.setDate.value = date
            },
            onSaveClickListenerMonth = {

            }
        ).show(parentFragmentManager, dialogDate)
    }

    private fun clickBack() {
        navigate(R.id.mainScreenFragment)
        corrd.visibility = View.VISIBLE
    }

    private fun createTask() =
        findNavController().safeNavigate(AddTaskFragmentDirections.fromAddTaskToMain())

    private fun NavController.safeNavigate(direction: NavDirections) {
        currentDestination?.getAction(direction.actionId)?.run {
            navigate(direction)
            hide()
            corrd.visibility = View.VISIBLE
        }
    }


}