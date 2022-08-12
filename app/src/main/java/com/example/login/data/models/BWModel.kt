package com.example.login.data.models

import android.graphics.ColorMatrix
import androidx.annotation.DrawableRes

data class BWModel(
    val type: ColorMatrix,
    @DrawableRes
    val img: Int = 0,
)