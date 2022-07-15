package com.example.login.ui.screens.mainScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.login.R
import com.example.login.adapters.DaysAdapter
import com.example.login.adapters.TasksAdapter
import com.example.login.arch.BaseFragment
import com.example.login.constants.Constants
import com.example.login.constants.Constants.dateFormatMonth
import com.example.login.constants.Constants.dialogDate
import com.example.login.data.DaysModel
import com.example.login.data.TaskModel
import com.example.login.databinding.FragmentMainScreenBinding
import com.example.login.repository.TaskRepository.hideImgAndTv
import com.example.login.ui.datePicker.DatePickerDialog
import com.example.login.utils.AppUtils.Companion.getCurrentMonth
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class MainScreenFragment : BaseFragment<FragmentMainScreenBinding>(R.layout.fragment_main_screen) {
    override val viewModel: MainScreenViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.viewmodel = viewModel
        requireActivity().onBackPressedDispatcher.addCallback(this) {}
        initRecyclerDays()
        initRecyclerTasks()
        return view
    }


    override fun setObservers() {
        super.setObservers()
        viewModel.choiceMonth.observe(this) {
            openDateDialog()
        }
        if (hideImgAndTv) {
            viewModel.hideImgAndTv.value = true
        }
        viewModel.clearEd.observe(this) {
            binding.editTextTextPersonName.text.clear()
        }
    }

    private fun initRecyclerTasks() {
        viewModel.tasks.observe(viewLifecycleOwner) {
            binding.recTasks.also { rec ->
                rec.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                rec.adapter = TasksAdapter(
                    viewModel.tasks.value as MutableList<TaskModel>,
                    requireContext(),
                    parentFragmentManager
                )
            }
        }
    }

    private fun initRecyclerDays() {
        viewModel.days.observe(viewLifecycleOwner) {
            binding.recyclerDays.also {
                it.layoutManager = GridLayoutManager(requireContext(), 7)
                it.adapter =
                    DaysAdapter(viewModel.days.value as MutableList<DaysModel>)
            }
        }
    }


    private fun openDateDialog() {
        DatePickerDialog(
            onSaveClickListenerDate = {

            },
            onSaveClickListenerMonth = { month ->
                viewModel.currentMonth.value = month
            }
        ).show(parentFragmentManager, dialogDate)
    }
}