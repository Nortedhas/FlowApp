package com.example.ageone.Network.WS

import io.socket.client.IO
import io.socket.client.Manager
import io.socket.client.Socket
import io.socket.engineio.client.Transport
import timber.log.Timber

fun connect() {
    val socket = IO.socket("http://176.119.157.149:80"
    )

    socket.io().on(Manager.EVENT_TRANSPORT) { args ->
        val transport = args[0] as Transport

        transport.on(Transport.EVENT_REQUEST_HEADERS) { args ->
            val headers = args[0] as MutableMap<String, List<String>>
            Timber.i("set access UserHandshake")
            // modify request headers
            headers["x-access-UserHandshake"] = listOf(
                "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6ImFiOGNjYmQ1LWR" +
                        "mMjktNGU2My05ZmM4LTdlZGZjM2VmNjJmMiIsImRldmljZUlkIjoicXdlcnR5Iiwi" +
                        "aXNBZG1pbiI6ZmFsc2UsImlhdCI6MTU2NDY2ODY2MCwiZXhwIjoxNTY0NjkwMjYwfQ.m7" +
                        "ddVqoTEZfUuJ94MOXOq-1JZb9EBeh_2Zg4UVsi75q1NO9CKrlFmM_5MBz9gbRd50SDbuZaLS_" +
                        "nPYC_LDoHkOk7hDTnWeGYgect1kmBExiephYULPcFxmoiL4Iz2gwb-0KhkhsKyzQ2pOyzYQXIBlBb" +
                        "pm99E2nuPYy8_bB6WPRNfaMZSusiBYXMTgSA_UCmNcrFRYm3CHFuUdtQNzqq2NJMDwZS0cVVfrC" +
                        "-PgISYKOx0_QJwrNbzxnzfHe9zuH5fzisBdnK6qIi2PMIps0H0t_E-VvmZUNUggRH0XV8jTZ0" +
                        "SG4Q7WwFgWVrJDkKVgEXmkATw0uvs3FKMqQNLtLv0Q")
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
//                socket.emit("foo", "hi")
            socket.emit("registration", "hi")
        }
        .on(Socket.EVENT_DISCONNECT) { println("disconnected") }

}