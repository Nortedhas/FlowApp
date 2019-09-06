package com.example.ageone.Modules

import com.example.ageone.External.Interfaces.InterfaceModel
import com.example.ageone.External.Interfaces.InterfaceViewModel
import com.example.ageone.Modules.Sets.SetsModel

class SetsFilterViewModel : InterfaceViewModel {
    var model = SetsFilterModel()

    fun initialize(recievedModel: InterfaceModel, completion: ()->(Unit)) {
        if (recievedModel is SetsFilterModel) {
            model = recievedModel
            completion.invoke()
        }
    }

    enum class EventType {
        OnSearchPressed
    }
}

class SetsFilterModel : InterfaceModel {

}
