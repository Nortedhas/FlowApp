package com.example.ageone.External.HTTP

import com.example.ageone.Application.api
import com.example.ageone.SCAG.DataBase
import com.example.ageone.SCAG.Parser
import org.json.JSONObject
import timber.log.Timber

//TODO: 3 func
fun DataBase.update(objectID: String, objectStruct: Map<String, Any>) {
    api.request(
        mapOf(
            "router" to "update",
            "collectionName" to name,
            "elementId" to objectID,
            "jsonValues" to objectStruct

        )) { jsonObject ->
        Timber.i("Object: $jsonObject")
    }
}

fun DataBase.delete(objectID: String) {
    api.request(
        mapOf(
            "router" to "delete",
            "collectionName" to name,
            "elementId" to objectID
        )) { jsonObject ->
        Timber.i("Object: $jsonObject")
    }
}

fun DataBase.fetch(filter: String, cashTime: Int = 0, completion: (JSONObject) -> (Unit)) {
    api.request(
        mapOf(
    "router" to "fetch",
    "collectionName" to name,
    "cashTime" to cashTime,
    "filter" to if (filter.isBlank()) "isExist = true" else "$filter && isExist = true"
    )) { jsonObject ->
        Timber.i("Object: $jsonObject")
    }
}
