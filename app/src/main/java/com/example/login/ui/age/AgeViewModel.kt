package com.example.login.ui.age

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.repo.AgeRepository
import com.example.login.ui.age.enums.AgeType

class AgeViewModel(
    private val ageRepository: AgeRepository
) : BaseViewModel() {
    val age: MutableLiveData<Int> = MutableLiveData(0)
    val ageToTakeInfo: MutableLiveData<String> = MutableLiveData()
    val currentImage: MutableLiveData<AgeType> = MutableLiveData()
    private val isMaleOrFemale: MutableLiveData<Boolean> = MutableLiveData()

    init {
    }

    fun getName() {
        launch {
            val result = ageRepository.getAgeByName(ageToTakeInfo.value.toString())
            val ageResult = result.body()?.age
            val name = result.body()?.name

            isMaleOrFemale.value = (name?.last() ?: "") != 'a'
            age.value = ageResult ?: 0

            checkMaleAge(ageResult ?: 0)
        }
    }

    private fun checkMaleAge(age: Int) {
        Log.d("test", "${currentImage.value}")
        if (age <= 4) {
            if (isMaleOrFemale.value == true) {
                currentImage.value = AgeType.THREE_M
            } else {
                currentImage.value = AgeType.THREE_F
            }
        }
        if (age <= 14 || age >= 5) {
            if (isMaleOrFemale.value == true) {
                currentImage.value = AgeType.ELEVENTH_M
            } else {
                currentImage.value = AgeType.ELEVENTH_F
            }
        }
        if (age >= 14 || age <= 18) {
            if (isMaleOrFemale.value == true) {
                currentImage.value = AgeType.SIXTEEN_M
            } else {
                currentImage.value = AgeType.SIXTEEN_F
            }
        }
        if (age >= 19 || age <= 34) {
            if (isMaleOrFemale.value == true) {
                currentImage.value = AgeType.THIRTY_FIVE_M
            } else {
                currentImage.value = AgeType.THIRTY_FIVE_F
            }
        }
        if (age > 35 || age <= 49) {
            if (isMaleOrFemale.value == true) {
                currentImage.value = AgeType.FOURTH_M
            } else {
                currentImage.value = AgeType.FOURTH_F
            }
        }
        if (age >= 50) {
            if (isMaleOrFemale.value == true) {
                currentImage.value = AgeType.SIXTEE_M
            } else {
                currentImage.value = AgeType.SIXTEE_F
            }
        }
    }

}