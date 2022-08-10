package com.example.login.ui.editor

import android.graphics.ColorMatrix
import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.data.Actions
import com.example.login.data.BWModel
import com.example.login.data.BWTypes
import com.example.login.repository.ImageRepository


class CutViewModel(
    private val repo: ImageRepository
) : BaseViewModel() {
    val actions: MutableLiveData<Actions> = MutableLiveData()
    val bwTypes: MutableLiveData<ColorMatrix> = MutableLiveData()
    val undoClickableState: MutableLiveData<Boolean> = MutableLiveData()
    val BWItems: MutableLiveData<List<BWModel>> = MutableLiveData()

    init {
        BWItems.value = repo.BWList
    }

    fun toHome() {
        actions.value = Actions.HOME
    }

    fun saveImage() {
        actions.value = Actions.SAVE_PHOTO
    }

    fun saveChanges() {
        undoClickableState.value = true
        actions.value = Actions.SAVE_CHANGES
    }

    fun resetImg() {
        actions.value = Actions.RESET
    }

    fun undoChanges() {
        actions.value = Actions.UNDO
    }

}