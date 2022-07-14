package com.example.login.ui.timePicker

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.format.DateFormat
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.login.R
import com.example.login.databinding.FragmentTimePickerBinding
import com.example.login.ui.dialogFragment.TagDialogViewModel
import java.util.*

class TimePickerDialog(
    private val onSaveClickListener: (String) -> Unit
) : DialogFragment() {
    private lateinit var binding: FragmentTimePickerBinding
    private val viewModel: TagDialogViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentTimePickerBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)
        var first = 0
        var second = 0

        val reminders = resources.getStringArray(R.array.reminder)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.drop_down_reminder, reminders)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)

        binding.firstPicker.setOnValueChangedListener { numberPicker, i, i2 ->
            first = numberPicker.value
        }

        binding.secondPicker.setOnValueChangedListener { numberPicker, i, i2 ->
            second = numberPicker.value
        }

        binding.btnSave.setOnClickListener {
            var result = formatTime(first, second)
            onSaveClickListener.invoke(result)
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

    private fun formatTime(hours : Int, minutes : Int): String {
        return when(true){
            (hours < 10 && minutes < 10) -> "0$hours:0$minutes"
            (hours < 10 && minutes > 10) -> "0$hours:$minutes"
            (hours > 10 && minutes < 10) -> "$hours:0$minutes"
            else -> "$hours:$minutes"
        }
    }
}


