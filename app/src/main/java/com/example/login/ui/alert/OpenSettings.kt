package com.example.login.ui.alert

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import com.example.login.data.constants.Constants

fun openSettings(activity: Activity){
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    val uri = Uri.fromParts(Constants.PACKAGE_TAG, activity.packageName, null)
    intent.data = uri
    activity.startActivity(intent)
}