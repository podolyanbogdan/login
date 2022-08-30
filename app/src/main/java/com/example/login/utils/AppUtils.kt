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
        private const val DATE_NAME_DAY = "EEEE"

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

        fun getDate(format: String): String {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern(format)
            return current.format(formatter)
        }

        fun getDayName(): String{
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern(DATE_NAME_DAY)
            return current.format(formatter)
        }

        fun currentDay(): Int{
            return Calendar.getInstance().get(Calendar.DAY_OF_WEEK)-2
        }
    }
}