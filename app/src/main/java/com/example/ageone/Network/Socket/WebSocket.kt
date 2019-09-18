package com.example.ageone.Network.Socket

import androidx.core.view.children
import com.example.ageone.Application.*
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.currentFlow
import com.example.ageone.External.Libraries.Alert.alertManager
import com.example.ageone.External.Libraries.Alert.single
import com.example.ageone.Models.User.user
import com.example.ageone.SCAG.DataBase
import io.realm.internal.sync.BaseModule
import io.socket.client.IO
import io.socket.client.Manager
import io.socket.client.Socket
import io.socket.engineio.client.Transport
import org.json.JSONObject
import timber.log.Timber
import java.net.URISyntaxException

class WebSocket {

    lateinit var socket: Socket

    fun initialize() {
        try {
            socket = IO.socket("${DataBase.url}:80")
            socket.connect()
            val body = JSONObject()
            body.put("token", utils.variable.token)
            socket.emit("registration", body)

            subscribeOrderCheck()
        } catch (e: Exception) {
            Timber.e("Socket connect error: ${e.message}")
        }
    }

    private fun subscribeOrderCheck() {
        webSocket.socket.on("orderCheck") { message ->
            Timber.i("Pay succes!")
            currentActivity?.runOnUiThread {
                router.onBackPressed()
                alertManager.single("Поздравляем!", "Оплата прошла успешно, для доступа к покупке перейдите на соотвествующий экран")

                api.handshake {  }
                api.requestMainLoad {  }
            }

        }
    }


    fun connect() {


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
            .on("orderCheck") {
                Timber.i("Socket Order check")
            }

        socket.connect()
            .on(Socket.EVENT_CONNECT) {
                Timber.i("connected")
            }
            .on(Socket.EVENT_DISCONNECT) { println("disconnected") }


    }
}
