package com.example.ageone.Modules.Registration

import com.example.ageone.External.Interfaces.InterfaceModel
import com.example.ageone.External.Interfaces.InterfaceViewModel
import com.example.ageone.Modules.PurchasesModel

class RegistrationViewModel: InterfaceViewModel {
    var model = RegistrationModel()

    fun initialize(recievedModel: InterfaceModel, completion: ()->(Unit)) {
        if (recievedModel is RegistrationModel) {
            model = recievedModel
            completion.invoke()
        }
    }

    enum class EventType{
        OnTestPressed,
        OnRegistrationPressed
    }
}

class RegistrationModel: InterfaceModel {
    var inputName = ""
    var inputPhone = ""
    var inputMail = ""
}