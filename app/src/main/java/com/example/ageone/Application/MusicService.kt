package com.example.ageone.Application

import android.app.Service
import android.content.Intent
import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.ResultReceiver
import androidx.core.net.toUri
import com.example.ageone.External.Libraries.Alert.alertManager
import com.example.ageone.External.Libraries.Alert.blockUI
import com.example.ageone.SCAG.Audio
import timber.log.Timber


const val playingMeditationNameService = "PLayingMeditationService"
const val receiver = "receiver"

val ACTION_PLAY = "com.example.ageone.intent.action.PLAY"
val ACTION_STOP = "com.example.ageone.intent.action.STOP"
val ACTION_SEEK_TO = "com.example.ageone.intent.action.SEEK_TO"

class MusicService : Service() {

    private var myPlayer: MediaPlayer? = null

    override fun onBind(intent: Intent): IBinder? {
        return null
    }


    override fun onCreate() {
        Timber.i("service on create")
        myPlayer = MediaPlayer.create(this, rxData.currentMeditation?.audio?.path()?.toUri())
        myPlayer?.setOnCompletionListener { player ->
            player.seekTo(0)
            rxData.isMeditationEnd = true
        }

    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        myPlayer?.let { player ->
            when (intent.action) {
                ACTION_PLAY -> {
                    player.start()
                }
                ACTION_STOP -> {
                    player.pause()
                }
                ACTION_SEEK_TO -> {
                    player.seekTo(rxData.currentTime.toInt())
                }
            }
        }

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        myPlayer?.stop()
    }
}

class MusicReceiver(handler: Handler) : ResultReceiver(handler) {

    override fun onReceiveResult(resultCode: Int, resultData: Bundle) {

        super.onReceiveResult(resultCode, resultData)

        if (resultCode == DownloadService.UPDATE_PROGRESS) {

            val progress = resultData.getInt("progress") //get the progress
            if (progress == 100) {
                alertManager.blockUI(false)
            }

        }
    }
}

fun Audio.getDuration(): Long {

    if (isAudioDownload()) {
        val uri = Uri.parse(path())
        val mmr = MediaMetadataRetriever()
        mmr.setDataSource(currentActivity, uri)
        val durationStr = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
        return durationStr.toLong()
    }

    return 0
}