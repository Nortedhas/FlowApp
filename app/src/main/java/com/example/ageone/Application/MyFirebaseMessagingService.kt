package com.example.ageone.Application

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import timber.log.Timber

class MyFirebaseMessagingService: FirebaseMessagingService() {
    override fun onMessageReceived(p0: RemoteMessage?) {
        Timber.i("${p0?.data}")
    }

    override fun onNewToken(token: String?) {
        Timber.i("$token")
    }
}