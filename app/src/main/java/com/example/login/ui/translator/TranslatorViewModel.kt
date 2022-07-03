package com.example.login.ui.translator

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.data.WordTranslatedModel
import com.example.login.room.RoomSingleton
import com.example.login.room.Word

class TranslatorViewModel(application: Application): BaseViewModel() {
    private val db:RoomSingleton = RoomSingleton.getInstance(application)
    internal val allWord : LiveData<List<Word>> = RoomSingleton.getInstance(application).wordDao().allWord()

    fun insert(word: Word){
        db.wordDao().insert(word)
    }
    fun delete(){
        db.clearAllTables()
    }


    private val _clickTranslate = MutableLiveData<Boolean>()
    val clickTranslate: LiveData<Boolean>
        get() = _clickTranslate

    val choiceLangTrigger: MutableLiveData<Boolean> = MutableLiveData()

    fun clickLanguage(){
        choiceLangTrigger.value = true
    }
    fun goResultFrag(){
        _clickTranslate.value = true
    }

}