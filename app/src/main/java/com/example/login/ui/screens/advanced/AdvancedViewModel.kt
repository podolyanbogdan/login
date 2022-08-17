package com.example.login.ui.screens.advanced

import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.arch.lifecycle.SingleLiveEvent
import com.example.login.data.constants.Const.CNT
import com.example.login.data.constants.Const.GEN
import com.example.login.data.constants.Const.LOC
import com.example.login.data.constants.Const.NO_COUNTRY
import com.example.login.data.constants.Const.REC
import com.example.login.data.constants.Const.RMK
import com.example.login.data.constants.Const.SP
import com.example.login.data.constants.Const.SSP
import com.example.login.data.constants.Const.TYPE
import com.example.login.data.enumss.FieldsStatus
import com.example.login.data.models.SearchModel
import com.example.login.data.enumss.From
import com.example.login.data.repository.BirdRepository

class AdvancedViewModel(
    private val repo: BirdRepository
) : BaseViewModel() {
    val onSearchTrigger: MutableLiveData<FieldsStatus> =  MutableLiveData()
    var searchModel = SearchModel()
    val wifiState: MutableLiveData<Boolean> = MutableLiveData()

    private fun requestString(): String {
        val result = mutableMapOf<String, String>()

        if (searchModel.country.isNotEmpty()) {
            result[CNT] = searchModel.country
        }
        if (searchModel.gen.isNotEmpty()) {
            result[GEN] = searchModel.gen
        }
        if (searchModel.type.isNotEmpty()) {
            result[SP] = searchModel.type
        }
        if (searchModel.subtype.isNotEmpty()) {
            result[SSP] = searchModel.subtype
        }
        if (searchModel.soundType.isNotEmpty()) {
            result[TYPE] = searchModel.soundType
        }
        if (searchModel.place.isNotEmpty()) {
            result[LOC] = searchModel.place
        }
        if (searchModel.remarks.isNotEmpty()) {
            result[RMK] = searchModel.remarks
        }
        if (searchModel.author.isNotEmpty()) {
            result[REC] = searchModel.author
        }

        return result.entries.joinToString(separator = " ").replace("=", ":")
    }

    private fun createSearchRequest() {
        if(searchModel.country == NO_COUNTRY){
            searchModel.country = ""
        }
        if(searchModel.country.isEmpty() && searchModel.gen.isEmpty()){
            onSearchTrigger.value = FieldsStatus.EMPTY
        } else {
            repo.getSearchType(From.ADVANCED)
            repo.getAdvancedRequest(requestString())
            onSearchTrigger.value = FieldsStatus.FILLED
        }
    }

    fun onSearch() {
        createSearchRequest()
    }
}