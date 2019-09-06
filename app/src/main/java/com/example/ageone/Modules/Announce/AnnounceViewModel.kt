package com.example.ageone.Modules

import com.example.ageone.External.Interfaces.InterfaceModel
import com.example.ageone.External.Interfaces.InterfaceViewModel

class AnnounceViewModel : InterfaceViewModel {
    var model = AnnounceModel()

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

}
