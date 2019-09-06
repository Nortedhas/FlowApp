package com.example.ageone.Modules.StartLogin

import com.example.ageone.External.Interfaces.InterfaceModel
import com.example.ageone.External.Interfaces.InterfaceViewModel
import com.example.ageone.Modules.Start.StartModel

class StartLoginViewModel: InterfaceViewModel {
    var model = StartLoginModel()

    fun initialize(recievedModel: InterfaceModel, completion: ()->(Unit)) {
        if (recievedModel is StartLoginModel) {
            model = recievedModel
            completion.invoke()
        }
    }

    enum class EventType{
        OnVkPressed,
        OnRegistrationPhonePressed,
        OnEntryPressed
    }
}

class StartLoginModel: InterfaceModel {

}