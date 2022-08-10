package com.example.login.repository

import android.graphics.Bitmap
import android.graphics.ColorMatrix
import com.example.login.R
import com.example.login.data.BWModel
import com.example.login.data.BWTypes

class ImageRepository {
    lateinit var bitToSave: Bitmap
    var lastColorMatrix = ColorMatrix()

    private val matrix1 = ColorMatrix(
        floatArrayOf(
            BWTypes.TYPES1.type, BWTypes.TYPES1.type, BWTypes.TYPES1.type, 0f, BWTypes.TYPES1.brigh,
            BWTypes.TYPES1.type, BWTypes.TYPES1.type, BWTypes.TYPES1.type, 0f, BWTypes.TYPES1.brigh,
            BWTypes.TYPES1.type, BWTypes.TYPES1.type, BWTypes.TYPES1.type, 0f, BWTypes.TYPES1.brigh,
            0f, 0f, 0f, 1f, 0f
        )
    )
    private val matrix2 = ColorMatrix(
        floatArrayOf(
            BWTypes.TYPES2.type, BWTypes.TYPES2.type, BWTypes.TYPES2.type, 0f, BWTypes.TYPES2.brigh,
            BWTypes.TYPES2.type, BWTypes.TYPES2.type, BWTypes.TYPES2.type, 0f, BWTypes.TYPES2.brigh,
            BWTypes.TYPES2.type, BWTypes.TYPES2.type, BWTypes.TYPES2.type, 0f, BWTypes.TYPES2.brigh,
            0f, 0f, 0f, 1f, 0f
        )
    )
    private val matrix3 = ColorMatrix(
        floatArrayOf(
            BWTypes.TYPES3.type, BWTypes.TYPES3.type, BWTypes.TYPES3.type, 0f, BWTypes.TYPES3.brigh,
            BWTypes.TYPES3.type, BWTypes.TYPES3.type, BWTypes.TYPES3.type, 0f, BWTypes.TYPES3.brigh,
            BWTypes.TYPES3.type, BWTypes.TYPES3.type, BWTypes.TYPES3.type, 0f, BWTypes.TYPES3.brigh,
            0f, 0f, 0f, 1f, 0f
        )
    )
    private val matrix4 = ColorMatrix(
        floatArrayOf(
            BWTypes.TYPES4.type, BWTypes.TYPES4.type, BWTypes.TYPES4.type, 0f, BWTypes.TYPES4.brigh,
            BWTypes.TYPES4.type, BWTypes.TYPES4.type, BWTypes.TYPES4.type, 0f, BWTypes.TYPES4.brigh,
            BWTypes.TYPES4.type, BWTypes.TYPES4.type, BWTypes.TYPES4.type, 0f, BWTypes.TYPES4.brigh,
            0f, 0f, 0f, 1f, 0f
        )
    )
    private val matrix5 = ColorMatrix(
        floatArrayOf(
            BWTypes.TYPES5.type, BWTypes.TYPES5.type, BWTypes.TYPES5.type, 0f, BWTypes.TYPES5.brigh,
            BWTypes.TYPES5.type, BWTypes.TYPES5.type, BWTypes.TYPES5.type, 0f, BWTypes.TYPES5.brigh,
            BWTypes.TYPES5.type, BWTypes.TYPES5.type, BWTypes.TYPES5.type, 0f, BWTypes.TYPES5.brigh,
            0f, 0f, 0f, 1f, 0f
        )
    )

    val BWList = listOf(
        BWModel(type = matrix1, img = R.drawable.avatar_2),
        BWModel(type = matrix2, img = R.drawable.avatar_3),
        BWModel(type = matrix3, img = R.drawable.avatar_4),
        BWModel(type = matrix4, img = R.drawable.avatar_5),
        BWModel(type = matrix5, img = R.drawable.avatar_6)
    )

}