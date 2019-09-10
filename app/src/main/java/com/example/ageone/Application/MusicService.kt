package com.example.ageone.Application

import android.widget.Toast
import android.content.Intent
import android.app.Service
import android.media.MediaPlayer
import android.os.IBinder
import androidx.core.net.toUri


class MusicService : Service() {
    lateinit var myPlayer: MediaPlayer

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        myPlayer = MediaPlayer.create(this, rxData.currentMeditation?.audio?.path()?.toUri())
    }

    override fun onStart(intent: Intent, startid: Int) {
        myPlayer.start()
    }

    override fun onDestroy() {
        myPlayer.stop()
    }
}