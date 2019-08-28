package com.example.ageone.Modules.Meditation

import com.example.ageone.External.Interfaces.InterfaceModel
import com.example.ageone.External.Interfaces.InterfaceViewModel

class MeditationViewModel: InterfaceViewModel {
    enum class EventType{
        OnEnterPressed,
        OnSearchPressed,
        OnMeditationPressed
    }
}

class MeditationModel: InterfaceModel {

}