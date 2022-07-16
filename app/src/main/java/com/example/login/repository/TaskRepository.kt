package com.example.login.repository

import com.example.login.data.TagsModel
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

    private var tagInside = mutableListOf<TagsModel>()
    fun getTagInside(tags: MutableList<TagsModel>){
        this.tagInside = tags
    }
    fun setTagInside(): MutableList<TagsModel> {
        return tagInside
    }

    var hideImgAndTv = false
    fun hide(){
        hideImgAndTv = true
    }

}