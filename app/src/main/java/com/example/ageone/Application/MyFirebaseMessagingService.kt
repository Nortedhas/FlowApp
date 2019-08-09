package com.example.ageone.Application

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import timber.log.Timber
import io.fabric.sdk.android.services.settings.IconRequest.build
import android.content.Context.NOTIFICATION_SERVICE
import androidx.core.content.ContextCompat.getSystemService
import android.app.NotificationManager
import android.R
import android.graphics.Color.parseColor
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.media.app.NotificationCompat


class MyFirebaseMessagingService: FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        Timber.i("${remoteMessage?.data}")

    }

    override fun onNewToken(token: String?) {
        Timber.i("$token")
    }
}