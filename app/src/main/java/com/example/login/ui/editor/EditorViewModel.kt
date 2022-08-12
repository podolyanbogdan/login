package com.example.login.ui.editor

import android.graphics.Bitmap
import android.graphics.ColorMatrix
import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.data.enums.Actions
import com.example.login.data.models.BWModel
import com.example.login.data.enums.BitmapStates
import com.example.login.repository.BWStorage
import com.example.login.repository.ImageRepository
import com.example.login.repository.initCanvas


class EditorViewModel(
    private val repo: ImageRepository,
    private val bwStorage: BWStorage
) : BaseViewModel() {
    val actions: MutableLiveData<Actions> = MutableLiveData()
    val bwTypes: MutableLiveData<ColorMatrix> = MutableLiveData()
    val undoClickableState: MutableLiveData<Boolean> = MutableLiveData()
    val BWItems: MutableLiveData<List<BWModel>> = MutableLiveData()

    val brightnessValue: MutableLiveData<Int> = MutableLiveData()
    val contrastValue: MutableLiveData<Int> = MutableLiveData()

    val mainBitmap: MutableLiveData<Bitmap?> = MutableLiveData()

    val savedProcessState: MutableLiveData<BitmapStates> = MutableLiveData()


    fun savedProcessState(state: BitmapStates){
        savedProcessState.value = state
    }

    fun saveBit(bit: Bitmap?){
        mainBitmap.value = bit
    }
    fun putBit(): Bitmap? {
        return mainBitmap.value
    }

    fun setMatrix(BWmatrix: ColorMatrix) {
        bwTypes.value = BWmatrix
    }

    init {
        BWItems.value = bwStorage.BWList
    }

    fun onValueChangedBrightness(value: Int) {
        brightnessValue.value = value
    }

    fun onValueChangedContrast(value: Int){
        contrastValue.value = value
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


    fun undoChanges() {
        actions.value = Actions.UNDO
    }

}