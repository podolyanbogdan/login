package com.example.login.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WordTranslatedModel(
    val init: String,
    val word: String,
    val wordTime: String = ""
): Parcelable