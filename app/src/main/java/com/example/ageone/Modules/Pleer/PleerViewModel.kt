package com.example.ageone.Modules

import com.example.ageone.External.Interfaces.InterfaceModel
import com.example.ageone.External.Interfaces.InterfaceViewModel

class PleerViewModel : InterfaceViewModel {
    var model = PleerModel()

    fun initialize(recievedModel: InterfaceModel, completion: ()->(Unit)) {
        if (recievedModel is PleerModel) {
            model = recievedModel
            completion.invoke()
        }
    }

    enum class EventType {

    }
}

class PleerModel : InterfaceModel {

}
