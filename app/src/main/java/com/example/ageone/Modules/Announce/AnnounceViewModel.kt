package com.example.ageone.Modules.Announce

import com.example.ageone.Application.utils
import com.example.ageone.External.Interfaces.InterfaceModel
import com.example.ageone.External.Interfaces.InterfaceViewModel
import com.example.ageone.SCAG.Announce
import com.example.ageone.SCAG.Enums
import com.example.ageone.SCAG.Product
import io.realm.Sort
import timber.log.Timber

class AnnounceViewModel : InterfaceViewModel {
    var model = AnnounceModel()

    var realmData = listOf<Announce>()
    fun loadRealmData() {
        realmData = utils.realm.announce.getAllObjects().sort("__type", Sort.DESCENDING) //TODO: DO
        model.countVideos = utils.realm.announce.getAllObjects().filter { announce ->
            announce.__type == Enums.AnnounceType.video.name
        }.size
        Timber.i("Announces: $realmData \nCount videos: ${model.countVideos}")
    }

    fun initialize(recievedModel: InterfaceModel, completion: ()->(Unit)) {
        if (recievedModel is AnnounceModel) {
            model = recievedModel
            completion.invoke()
        }
    }

    enum class EventType {

    }
}

class AnnounceModel : InterfaceModel {
    var countVideos = 0
}
