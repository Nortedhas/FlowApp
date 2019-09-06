package com.example.ageone.Modules

import com.example.ageone.External.Interfaces.InterfaceModel
import com.example.ageone.External.Interfaces.InterfaceViewModel

class EntrySMSViewModel : InterfaceViewModel {
    var model = EntryModel()

    fun initialize(recievedModel: InterfaceModel, completion: ()->(Unit)) {
        if (recievedModel is EntryModel) {
            model = recievedModel
            completion.invoke()
        }
    }


    enum class EventType {

    }
}

class EntrySMSModel : InterfaceModel {

}
