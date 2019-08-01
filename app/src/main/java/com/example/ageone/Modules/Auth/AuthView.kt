package com.example.ageone.Modules.Auth

import android.content.Context
import android.graphics.Color
import android.widget.Button
import com.example.ageone.Application.currentActivity
import com.example.ageone.External.Base.Button.BaseButton
import com.example.ageone.External.Base.Module.BaseModule
import io.socket.client.IO
import io.socket.client.Manager
import io.socket.client.Socket
import io.socket.engineio.client.Transport
import timber.log.Timber
import yummypets.com.stevia.constrainTopToBottomOf
import yummypets.com.stevia.subviews

class AuthView(context: Context?): BaseModule(context) {

    init {
        setBackgroundColor(Color.LTGRAY)
        val btn = BaseButton()
        innerContent.subviews(
            btn
        )
        btn.text = "Some"

        btn.constrainTopToBottomOf(toolBar, 8)

        /*btn.setOnClickListener {
            emitEvent?.invoke(AuthViewModel.EventType.OnButtonPressed.toString())
        }*/

        btn.setOnClickListener {
            val socket = IO.socket("http://localhost")

            socket.on(Socket.EVENT_CONNECT) {
                Timber.i("set message")
                socket.emit("foo", "hi")
                socket.disconnect()
            }.on("event") {

            }.on(Socket.EVENT_DISCONNECT) {

            }

            socket.connect()

            // Called upon transport creation.
//            val socket = IO.socket("http://localhost")

            socket.io().on(Manager.EVENT_TRANSPORT) { args ->
                val transport = args[0] as Transport

                transport.on(Transport.EVENT_REQUEST_HEADERS) { args ->
                    val headers = args[0] as MutableMap<String, List<String>>
                    Timber.i("set access token")
                    // modify request headers
                    headers["x-access-token"] = listOf("1111")
                }

                transport.on(Transport.EVENT_RESPONSE_HEADERS) { args ->
                    val headers = args[0] as Map<String, List<String>>
                    // access response headers
                    val cookie = headers["Set-Cookie"]?.get(0)
                }
            }

            socket.send()
        }
    }
}