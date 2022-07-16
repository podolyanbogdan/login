package com.example.login.ui.screens.addTask

import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.constants.Constants.dateDefaultValue
import com.example.login.constants.Constants.empty
import com.example.login.constants.Constants.timeDefaultValue1
import com.example.login.constants.Constants.timeDefaultValue2
import com.example.login.data.TagsModel
import com.example.login.data.TaskModel
import com.example.login.repository.TaskRepository

class AddTaskViewModel : BaseViewModel() {
    private var taskTitle: String = empty
    fun putTaskTitle(s: CharSequence) {
        this.taskTitle = s.toString()
    }

    private var descTitle: String = ""
    fun putDateTitle(s: CharSequence) {
        this.descTitle = s.toString()
    }

    val tags: MutableLiveData<List<TagsModel>> = MutableLiveData()

    val hideTvTag: MutableLiveData<Boolean> = MutableLiveData()

    val backPressed: MutableLiveData<Boolean> = MutableLiveData()

    val takeDate: MutableLiveData<Boolean> = MutableLiveData()
    val setDate: MutableLiveData<String> = MutableLiveData()
    val setTimeFirst: MutableLiveData<String> = MutableLiveData()
    val setTimeSecond: MutableLiveData<String> = MutableLiveData()
    val takeTimeFirst: MutableLiveData<Boolean> = MutableLiveData()
    val takeTimeSecond: MutableLiveData<Boolean> = MutableLiveData()

    val showTagDialog: MutableLiveData<Boolean> = MutableLiveData()

    val createTask: MutableLiveData<Boolean> = MutableLiveData()

    val descSettings: MutableLiveData<Boolean> = MutableLiveData()


    init {
        setTimeFirst.value = timeDefaultValue1
        setTimeSecond.value = timeDefaultValue2
        setDate.value = dateDefaultValue
    }

    fun descSettingsClick() {
        descSettings.value = true
    }

    fun backClicked() {
        backPressed.value = true
    }

    fun takeDate() {
        takeDate.value = true
    }

    fun takeTimeFirst() {
        takeTimeFirst.value = true
    }

    fun takeTimeSecond() {
        takeTimeSecond.value = true
    }

    fun showTagDialog() {
        showTagDialog.value = true
        hideTvTag.value = true
    }

    fun createTask() {
        createTask.value = true
        val data = TaskModel(
            title = taskTitle,
            date = setDate.value,
            timeFirst = setTimeFirst.value,
            timeBoth = "${setTimeFirst.value} - ${setTimeSecond.value}",
            description = descTitle,
            tags = TaskRepository.setTag()
        )
        TaskRepository.addTask(data)
    }
}