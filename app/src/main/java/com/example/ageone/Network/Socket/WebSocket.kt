package com.example.ageone.Network.Socket

import com.example.ageone.Application.utils
import com.example.ageone.SCAG.DataBase
import io.socket.client.IO
import io.socket.client.Manager
import io.socket.client.Socket
import io.socket.engineio.client.Transport
import timber.log.Timber

class WebSocket {
    fun connect() {
        val socket = IO.socket("${DataBase.url}:80"
        )

        socket.io().on(Manager.EVENT_TRANSPORT) { args ->
            val transport = args[0] as Transport

            transport.on(Transport.EVENT_REQUEST_HEADERS) { args ->
                val headers = args[0] as MutableMap<String, List<String>>
                Timber.i("set access UserHandshake")
                // modify request headers
                headers["x-access-UserHandshake"] = listOf(utils.variable.token)
            }

            transport.on(Transport.EVENT_RESPONSE_HEADERS) { args ->
                val headers = args[0] as Map<String, List<String>>
                // access response headers
                val cookie = headers["Set-Cookie"]?.get(0)
            }
        }

        socket.connect()
            .on(Socket.EVENT_CONNECT) {
                println("connected")
                Timber.i("set message")
                socket.emit("registration", "hi")
            }
            .on(Socket.EVENT_DISCONNECT) { println("disconnected") }

    }
}
