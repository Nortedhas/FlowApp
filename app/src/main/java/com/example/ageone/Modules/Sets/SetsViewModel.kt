package com.example.ageone.Modules.Sets

import com.example.ageone.External.Interfaces.InterfaceModel
import com.example.ageone.External.Interfaces.InterfaceViewModel
import com.example.ageone.Modules.Registration.RegistrationModel

class SetsViewModel: InterfaceViewModel {
    var model = SetsModel()

    fun initialize(recievedModel: InterfaceModel, completion: ()->(Unit)) {
        if (recievedModel is SetsModel) {
            model = recievedModel
            completion.invoke()
        }
    }

    enum class EventType{
        OnTestPressed,
        OnSetPressed;
    }
}

class SetsModel: InterfaceModel {

}