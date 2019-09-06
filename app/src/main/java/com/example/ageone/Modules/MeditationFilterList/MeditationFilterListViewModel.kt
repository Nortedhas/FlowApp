package com.example.ageone.Modules

import com.example.ageone.External.Interfaces.InterfaceModel
import com.example.ageone.External.Interfaces.InterfaceViewModel

class MeditationFilterListViewModel : InterfaceViewModel {
    var model = MeditationFilterListModel()

    fun initialize(recievedModel: InterfaceModel, completion: ()->(Unit)) {
        if (recievedModel is MeditationFilterListModel) {
            model = recievedModel
            completion.invoke()
        }
    }

    enum class EventType {
        OnMeditationPressed
    }
}

class MeditationFilterListModel : InterfaceModel {

}
