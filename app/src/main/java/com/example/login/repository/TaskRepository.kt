package com.example.login.repository

import androidx.databinding.ObservableField
import com.example.login.data.DaysModel
import com.example.login.data.TaskModel

object TaskRepository {
    private val taskList = mutableListOf<TaskModel>()
    fun addTask(taskModel: TaskModel){
        taskList.add(taskModel)
    }
    fun getTask(): MutableList<TaskModel>{
        return taskList
    }

    private var tagResult = ""
    fun getTag(tag: String){
        this.tagResult = tag
    }
    fun setTag(): String{
        return tagResult
    }

    var hideImgAndTv = false
    fun hide(){
        hideImgAndTv = true
    }

}