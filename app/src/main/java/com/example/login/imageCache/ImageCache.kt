package com.example.login.imageCache

import android.graphics.Bitmap
import androidx.collection.LruCache

class ImageCache private constructor() {

    private object HOLDER {
        val INSTANCE = ImageCache()
    }

    companion object {
        val instance: ImageCache by lazy { HOLDER.INSTANCE }
    }
    private val lru: LruCache<Any, Any> = LruCache(1024)

    fun saveBitmapToCahche(key: String, bitmap: Bitmap) {

        try {
            instance.lru.put(key, bitmap)
        } catch (e: Exception) {
        }

    }

    fun retrieveBitmapFromCache(key: String): Bitmap? {

        try {
            return instance.lru.get(key) as Bitmap?
        } catch (e: Exception) {
        }

        return null
    }

}