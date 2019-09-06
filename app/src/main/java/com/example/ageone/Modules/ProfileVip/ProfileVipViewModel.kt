package com.example.ageone.Modules

import com.example.ageone.External.Interfaces.InterfaceModel
import com.example.ageone.External.Interfaces.InterfaceViewModel

class ProfileVipViewModel : InterfaceViewModel {
    var model = ProfileVipModel()

    fun initialize(recievedModel: InterfaceModel, completion: ()->(Unit)) {
        if (recievedModel is ProfileVipModel) {
            model = recievedModel
            completion.invoke()
        }
    }

    enum class EventType {

    }
}

class ProfileVipModel : InterfaceModel {

}
