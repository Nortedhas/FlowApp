package com.example.ageone.Modules.Auth

import android.content.Context
import android.graphics.Color
import android.widget.Button
import com.example.ageone.Application.currentActivity
import com.example.ageone.External.Base.Button.BaseButton
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.TextView.BaseTextView
import io.socket.client.IO
import io.socket.client.Manager
import io.socket.client.Socket
import io.socket.engineio.client.Transport
import timber.log.Timber
import yummypets.com.stevia.constrainTopToBottomOf
import yummypets.com.stevia.subviews

class AuthView(context: Context?): BaseModule(/*context*/) {

    init {
        setBackgroundColor(Color.LTGRAY)
        val btn = BaseButton()

        innerContent.subviews(
            btn
        )
        btn.text = "Some"

        btn.constrainTopToBottomOf(toolBar, 8)

        btn.setOnClickListener {
            emitEvent?.invoke(AuthViewModel.EventType.OnButtonPressed.toString())
        }

    }

}