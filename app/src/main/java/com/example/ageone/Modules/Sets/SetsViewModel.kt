package com.example.ageone.Modules.Sets

import com.example.ageone.External.Interfaces.InterfaceModel
import com.example.ageone.External.Interfaces.InterfaceViewModel

class SetsViewModel: InterfaceViewModel {
    enum class EventType{
        OnTestPressed,
        OnSetPressed;
    }
}

class SetsModel: InterfaceModel {

}