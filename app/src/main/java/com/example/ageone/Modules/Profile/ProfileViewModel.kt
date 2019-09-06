package com.example.ageone.Modules

import com.example.ageone.External.Interfaces.InterfaceModel
import com.example.ageone.External.Interfaces.InterfaceViewModel

class ProfileViewModel : InterfaceViewModel {
    var model = ProfileModel()

    fun initialize(recievedModel: InterfaceModel, completion: ()->(Unit)) {
        if (recievedModel is ProfileModel) {
            model = recievedModel
            completion.invoke()
        }
    }

    enum class EventType {
        OnGetVipPressed;
    }
}

class ProfileModel : InterfaceModel {

}
