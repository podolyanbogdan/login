package com.example.login.ui.alert

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import com.example.login.R

class PermAlert(
    private val context: Context,
    private val activity: Activity
) {
    fun showRotationalDialogForPermission() {
        AlertDialog.Builder(context)
            .setMessage(
                activity.getString(R.string.alert_message_off_perm) + activity.getString(R.string.alert_message_off_perm_2)
            )
            .setPositiveButton(activity.getString(R.string.go_to_settings)) { _, _ ->
                openSettings(activity)
            }
            .setNegativeButton(activity.getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }.show()
    }
}