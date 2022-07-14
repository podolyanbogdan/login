package com.example.login.ui.datePicker

import android.app.AlertDialog
import android.app.Dialog
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.login.databinding.FragmentDatePickerBinding
import com.example.login.ui.screens.addTask.AddTaskViewModel
import java.text.SimpleDateFormat
import java.util.*


class DatePickerDialog(
    private val onSaveClickListener: (String) -> Unit
) : DialogFragment() {
    private lateinit var binding: FragmentDatePickerBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentDatePickerBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)

        var date = ""
        val locale = Locale("en_US")
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        context!!.applicationContext.resources.updateConfiguration(config, null)
        binding.calendarView.setOnDateChangeListener { calendarView, year, month, day ->
            val sdf = SimpleDateFormat("EEEE d MMMM")
            val selectedDates = sdf.format(Date(year - 1900, month, day))
            date = selectedDates
        }
        binding.btnSave.setOnClickListener {
            onSaveClickListener.invoke(date)
            dismiss()
        }
        binding.btnCancel.setOnClickListener {
            dismiss()
        }

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.setGravity(Gravity.CENTER)
        return dialog
    }


}