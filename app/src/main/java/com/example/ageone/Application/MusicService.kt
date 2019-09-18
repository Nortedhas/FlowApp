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


const val receiver = "receiver"

val ACTION_PLAY = "com.example.ageone.intent.action.PLAY"
val ACTION_STOP = "com.example.ageone.intent.action.STOP"
val ACTION_SEEK_TO = "com.example.ageone.intent.action.SEEK_TO"
val ACTION_VOLUME = "com.example.ageone.intent.action.VOLUME"
val ACTION_CHANGE_SOUND = "com.example.ageone.intent.action.CHANGE_SOUND"

class MusicService : Service() {

    private var mainPlayer: MediaPlayer? = null
    private var backgroundPlayer: MediaPlayer? = null

    private var backgroundSounds = arrayOf(
        R.raw.background_sound1,
        R.raw.background_sound2,
        R.raw.background_sound3,
        R.raw.background_sound4
    )

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        Timber.i("service on create")
        rxData.currentMeditation?.audio?.let { audio ->
            mainPlayer = MediaPlayer.create(this, audio.path().toUri())
        }
        mainPlayer?.setOnCompletionListener { player ->
            player.seekTo(0)
            rxData.isMeditationEnd = true
        }

        backgroundPlayer = MediaPlayer.create(currentActivity, backgroundSounds[rxData.currentBackground])
        backgroundPlayer?.isLooping = true
        backgroundPlayer?.setVolume(rxData.volumeBackground, rxData.volumeBackground)
    }

    var isPlaying = false
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        when (intent.action) {
            ACTION_PLAY -> {
                mainPlayer?.start()
                backgroundPlayer?.start()
                isPlaying = true
            }
            ACTION_STOP -> {
                mainPlayer?.pause()
                backgroundPlayer?.pause()
                isPlaying = false
            }
            ACTION_SEEK_TO -> {
                mainPlayer?.seekTo(rxData.currentTime.toInt())
            }
            ACTION_VOLUME -> {
                backgroundPlayer?.setVolume(rxData.volumeBackground, rxData.volumeBackground)
            }
            ACTION_CHANGE_SOUND -> {
                backgroundPlayer?.stop()
                backgroundPlayer = MediaPlayer.create(currentActivity, backgroundSounds[rxData.currentBackground])
                backgroundPlayer?.setVolume(rxData.volumeBackground, rxData.volumeBackground)
                if (isPlaying) {
                    backgroundPlayer?.start()
                }
            }
        }

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        mainPlayer?.stop()
        backgroundPlayer?.stop()
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