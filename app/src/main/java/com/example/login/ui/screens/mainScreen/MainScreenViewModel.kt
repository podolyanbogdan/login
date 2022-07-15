package com.example.login.ui.screens.mainScreen

import android.content.Context
import android.content.res.Configuration
import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.constants.Constants.langFormat
import com.example.login.constants.Constants.patternDayNumber
import com.example.login.constants.Constants.patternDayWord
import com.example.login.data.DaysModel
import com.example.login.data.TagsModel
import com.example.login.data.TaskModel
import com.example.login.repository.TaskRepository
import com.example.login.utils.AppUtils.Companion.getCurrentMonth
import com.example.login.utils.AppUtils.Companion.getCurrentTime
import java.text.SimpleDateFormat
import java.util.*


class MainScreenViewModel(context: Context) : BaseViewModel() {
    val days: MutableLiveData<List<DaysModel>> = MutableLiveData()
    val clearEd: MutableLiveData<Boolean> = MutableLiveData()
    val searchEd: MutableLiveData<Boolean> = MutableLiveData()
    val choiceMonth: MutableLiveData<Boolean> = MutableLiveData()
    val hideImgAndTv: MutableLiveData<Boolean> = MutableLiveData()
    val currentTime: MutableLiveData<String> = MutableLiveData()
    val currentMonth: MutableLiveData<String> = MutableLiveData()
    private val tasksRepo = TaskRepository.getTask()
    val tasks: MutableLiveData<List<TaskModel>> = MutableLiveData()
    val tags: MutableLiveData<List<TagsModel>> = MutableLiveData()

    init {
        currentMonth.value = getCurrentMonth()
        currentTime.value = getCurrentTime()
        days.value = getDaysList(context = context)
        tasks.value = tasksRepo
    }

    fun choiceMonth() {
        choiceMonth.value = true
    }

    fun clearEd() {
        clearEd.value = true
    }

    fun searchEd() {
        searchEd.value = true
    }


    private fun getDaysList(context: Context): MutableList<DaysModel> {
        val daysList = mutableListOf<DaysModel>()

        val locale = Locale(langFormat)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        context.applicationContext.resources.updateConfiguration(config, null)

        val cal = Calendar.getInstance()
        cal[Calendar.DAY_OF_WEEK] = Calendar.MONDAY
        val sdf = SimpleDateFormat(patternDayWord)
        val sdf2 = SimpleDateFormat(patternDayNumber)
        for (i in 0..6) {
            daysList.add(
                DaysModel(
                    dayWord = sdf.format(cal.time).slice(0..1),
                    dayNumber = sdf2.format(cal.time),
                )
            )
            cal.add(Calendar.DAY_OF_WEEK, 1)
        }
        return daysList
    }

}