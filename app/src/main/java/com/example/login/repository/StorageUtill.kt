package com.example.login.repository

import android.graphics.*
import android.os.Build

inline fun <T> sdk29AndUp(onSdk29: () -> T): T? {
    return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        onSdk29()
    } else null
}

fun initCanvas(repo: ImageRepository): Bitmap? {
    val ret = Bitmap.createBitmap(repo.bitToSave.width, repo.bitToSave.height, repo.bitToSave.config)
    val canvas = Canvas(ret)
    val paint = Paint()
    paint.colorFilter = ColorMatrixColorFilter(repo.lastColorMatrix)
    canvas.drawBitmap(repo.bitToSave, 0F, 0F, paint)
    return ret
}


