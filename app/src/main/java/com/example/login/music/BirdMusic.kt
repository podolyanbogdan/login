package com.example.login.music

import android.app.Activity
import android.app.DownloadManager
import android.content.Context
import android.content.Context.DOWNLOAD_SERVICE
import android.media.MediaPlayer
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import com.example.login.R
import com.example.login.data.constants.Const.FILE_FORMAT
import com.example.login.data.constants.Const.MIME_TYPE
import org.jetbrains.anko.doAsync


object BirdMusic {
    private var player = MediaPlayer()
    private var disableStart = false

    fun startMusic(context: Context, musicPath: String) {
      if(!disableStart){
          doAsync {
              player = MediaPlayer.create(context, Uri.parse(musicPath))
              player.start()
              player.isLooping = true
          }
          Toast.makeText(context, context.getString(R.string.music_is_loading), Toast.LENGTH_SHORT).show()
          disableStart = true
      }
    }

    fun stopMusic() {
        disableStart = false
        player.stop()
    }

    fun downloadMusic(musicPath: String, musicName: String, activity: Activity) {
        val request = DownloadManager.Request(Uri.parse(musicPath))
            .setTitle(musicName)
            .setDescription("Downloading $musicName")
            .setMimeType(MIME_TYPE)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "$musicName$FILE_FORMAT")
            .setAllowedOverMetered(true)
        val dm = activity.getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        dm.enqueue(request)
    }
}