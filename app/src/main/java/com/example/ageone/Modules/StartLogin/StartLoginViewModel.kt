package com.example.ageone.Modules.StartLogin

import com.example.ageone.External.Interfaces.InterfaceModel
import com.example.ageone.External.Interfaces.InterfaceViewModel

class StartLoginViewModel: InterfaceViewModel {
    enum class EventType{
        OnVkPressed,
        OnRegistrationPhonePressed
    }
}

class StartLoginModel: InterfaceModel {

}