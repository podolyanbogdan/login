package com.example.login.imageUtils

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContract
import com.example.login.R
import com.example.login.repository.sdk29AndUp
import com.theartofdev.edmodo.cropper.CropImage
import java.io.FileDescriptor
import java.io.IOException

class ImageUtils(
    private val context: Context,
    private val activity: Activity
) {

    val cropActivityResultContract = object : ActivityResultContract<Any?, Uri?>() {
        override fun createIntent(context: Context, input: Any?): Intent {
            return CropImage.activity()
                .getIntent(context)

        }

        override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
            CropImage.isExplicitCameraPermissionRequired(context)
            return CropImage.getActivityResult(intent).uri
        }

    }

    // save image to gallery from current uri
    fun savePhotoToExternalStorage(displayName: String, bmp: Bitmap): Boolean {
        val imageCollection = sdk29AndUp {
            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
        } ?: MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "$displayName.jpg")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.WIDTH, bmp.width)
            put(MediaStore.Images.Media.HEIGHT, bmp.height)
        }
        return try {
            activity.contentResolver.insert(imageCollection, contentValues)?.also { uri ->
                activity.contentResolver.openOutputStream(uri).use { outputStream ->
                    if (!bmp.compress(Bitmap.CompressFormat.JPEG, 95, outputStream)) {
                        throw IOException(activity.getString(R.string.couldnt_save_bitmap))
                    }
                }
            } ?: throw IOException(activity.getString(R.string.couldnt_create_mediastore_entry))
            true
        } catch (e: IOException) {
            e.printStackTrace()
            false
        }
    }

}