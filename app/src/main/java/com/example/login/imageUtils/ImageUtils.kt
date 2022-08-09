package com.example.login.imageUtils

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContract
import androidx.annotation.RequiresApi
import com.example.login.R
import com.example.login.repository.sdk29AndUp
import com.theartofdev.edmodo.cropper.CropImage
import java.io.FileDescriptor
import java.io.IOException

class ImageUtils(
    private val context: Context,
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

}