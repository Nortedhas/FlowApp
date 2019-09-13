package com.example.ageone.Modules

import com.example.ageone.External.Interfaces.InterfaceModel
import com.example.ageone.External.Interfaces.InterfaceViewModel

class EntrySMSViewModel : InterfaceViewModel {
    var model = EntrySMSModel()

    fun initialize(recievedModel: InterfaceModel, completion: ()->(Unit)) {
        if (recievedModel is EntrySMSModel) {
            model = recievedModel
            completion.invoke()
        }
    }


    enum class EventType {
        OnAcceptPressed
    }
}

class EntrySMSModel : InterfaceModel {
    var inputPhone = ""
    var code = ""
}
