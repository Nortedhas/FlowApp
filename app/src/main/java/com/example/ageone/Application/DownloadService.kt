package com.example.ageone.Application

import android.app.IntentService
import android.content.Intent
import android.os.*
import com.example.ageone.External.Libraries.Alert.alertManager
import com.example.ageone.External.Libraries.Alert.blockUI
import com.example.ageone.SCAG.Audio
import timber.log.Timber
import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.URL


val packagePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath + "/flow/"

class DownloadService : IntentService("DownloadService") {
    override fun onHandleIntent(intent: Intent) {

        Timber.i("Start download")
        val urlToDownload = intent.getStringExtra("url")
        val name = intent.getStringExtra("name")
        val receiver = intent.getParcelableExtra<Parcelable>("receiver") as ResultReceiver
        try {
            //create url and connect
            val url = URL(urlToDownload)
            val connection = url.openConnection()
            connection.connect()

            // download the file
            val input = BufferedInputStream(connection.getInputStream())

            val packageDir = File(packagePath)
            if (!packageDir.exists()) {
                packageDir.mkdirs()
            }

            val file = File("$packagePath/$name")
            if (!file.exists()) {
                file.createNewFile()
            }

            Timber.i("Download to: $file")
            val output = FileOutputStream(file)

            val data = ByteArray(1024)
            var count: Int = input.read(data)
            while (count != -1) {
                output.write(data, 0, count)
                count = input.read(data)
            }

            // close streams
            output.flush()
            output.close()
            input.close()

        } catch (e: IOException) {
            Timber.e("Error download: ${e.message}")
        }

        val resultData = Bundle()
        resultData.putInt("progress", 100)

        receiver.send(UPDATE_PROGRESS, resultData)
    }

    companion object {
        val UPDATE_PROGRESS = 8344
    }
}

class DownloadReceiver(handler: Handler) : ResultReceiver(handler) {

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

fun Audio.isAudioDownload(): Boolean = File(path()).exists()

fun Audio.path() = "$packagePath/$sourceName"