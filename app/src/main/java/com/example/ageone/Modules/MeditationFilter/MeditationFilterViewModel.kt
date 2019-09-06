package com.example.ageone.Modules

import com.example.ageone.External.Interfaces.InterfaceModel
import com.example.ageone.External.Interfaces.InterfaceViewModel

class MeditationFilterViewModel : InterfaceViewModel {
    var model = MeditationFilterModel()

    fun initialize(recievedModel: InterfaceModel, completion: ()->(Unit)) {
        if (recievedModel is MeditationFilterModel) {
            model = recievedModel
            completion.invoke()
        }
    }

    enum class EventType {
        OnSearchPressed
    }
}

class MeditationFilterModel : InterfaceModel {

}
