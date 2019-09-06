package com.example.ageone.Modules

import com.example.ageone.External.Interfaces.InterfaceModel
import com.example.ageone.External.Interfaces.InterfaceViewModel

class SetsFilterListViewModel : InterfaceViewModel {
    var model = SetsFilterListModel()

    fun initialize(recievedModel: InterfaceModel, completion: ()->(Unit)) {
        if (recievedModel is SetsFilterListModel) {
            model = recievedModel
            completion.invoke()
        }
    }

    enum class EventType {
        OnSetPressed

    }
}

class SetsFilterListModel: InterfaceModel {

}
