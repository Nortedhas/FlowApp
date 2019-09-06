package com.example.ageone.Modules

import com.example.ageone.External.Interfaces.InterfaceModel
import com.example.ageone.External.Interfaces.InterfaceViewModel

class SetsInViewModel : InterfaceViewModel {
    var model = SetsInModel()

    fun initialize(recievedModel: InterfaceModel, completion: ()->(Unit)) {
        if (recievedModel is SetsInModel) {
            model = recievedModel
            completion.invoke()
        }
    }

    enum class EventType {
        OnMeditationPressed
    }
}

class SetsInModel : InterfaceModel {

}
