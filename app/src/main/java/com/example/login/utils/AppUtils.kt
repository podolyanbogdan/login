package com.example.login.utils

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class AppUtils {

    companion object {
        // "2022-04-26 17:11:40"
        private const val DATE_API_SHORT_FORMAT = "yyyy-MM-dd HH:mm:ss"
        private const val DATE_APP_SHORT_FORMAT = "dd MMM"

        @SuppressLint("ConstantLocale")
        private val shortFormat = SimpleDateFormat(DATE_API_SHORT_FORMAT, Locale.getDefault())

        private fun getDateShortFormat(raw: String?): Date {
            var newDate: Date? = null
            raw?.let {
                try {
                    newDate = shortFormat.parse(it)
                } catch (e: ParseException) {
                    e.printStackTrace()
                }
            }
            return if (newDate != null) {
                newDate as Date
            } else {
                Date()
            }
        }

        fun getDate(dateTime: String): String {
            val date = getDateShortFormat(dateTime)
            return SimpleDateFormat(DATE_APP_SHORT_FORMAT, Locale.getDefault()).format(date)
        }

        fun getCurrentDate(): String {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            return current.format(formatter)
        }

    }
}