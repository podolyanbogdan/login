package com.example.login.repository

import android.graphics.Bitmap
import android.graphics.ColorMatrix


private var currentImagePath = ""

class ImageRepository() {
    lateinit var bitToSave: Bitmap
    var lastColorMatrix = ColorMatrix()
    fun saveCurrentImagePath(path: String){
        currentImagePath = path
    }
    fun takeCurrentImagePath(): String{
        return currentImagePath
    }

    fun setBitToSave(): Bitmap {
        return bitToSave
    }
    fun setCurrentColorMatrix(): ColorMatrix {
        return lastColorMatrix
    }

}