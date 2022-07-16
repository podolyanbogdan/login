package com.example.login.ui.datePicker

import android.app.AlertDialog
import android.app.Dialog
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.login.constants.Constants.dateFormat
import com.example.login.constants.Constants.dateFormatMonth
import com.example.login.constants.Constants.langFormat
import com.example.login.databinding.FragmentDatePickerBinding
import java.text.SimpleDateFormat
import java.util.*


class DatePickerDialog(
    private val onSaveClickListenerDate: (String) -> Unit,
    private val onSaveClickListenerMonth: (String) -> Unit,
) : DialogFragment() {
    private lateinit var binding: FragmentDatePickerBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentDatePickerBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)

        var date = ""
        var currentMonth = ""

        setLocale()

        binding.calendarView.setOnDateChangeListener { calendarView, year, month, day ->
            val sdfDate = SimpleDateFormat(dateFormat)
            val sdfMonth = SimpleDateFormat(dateFormatMonth)
            val selectedDates = sdfDate.format(Date(year - 1900, month, day))
            val selectedMonth = sdfMonth.format(Date(year - 1900, month, day))
            date = selectedDates.format()
            currentMonth = selectedMonth.format()
        }
        binding.btnSave.setOnClickListener {
            onSaveClickListenerDate.invoke(date)
            onSaveClickListenerMonth.invoke(currentMonth)
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

    private fun setLocale() {
        val locale = Locale(langFormat)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        context!!.applicationContext.resources.updateConfiguration(config, null)
    }

}