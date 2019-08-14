package com.example.ageone.Modules.Auth

import com.example.ageone.External.Interfaces.InterfaceModel
import com.example.ageone.External.Interfaces.InterfaceViewModel

class AuthViewModel: InterfaceViewModel {
    enum class EventType {
        OnButtonPressed,
        OnButonOpenNavPressed
    }
}

class AuthModel: InterfaceModel {

}