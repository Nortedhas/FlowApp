package com.example.ageone.Modules.Meditation

import com.example.ageone.Application.utils
import com.example.ageone.External.Interfaces.InterfaceModel
import com.example.ageone.External.Interfaces.InterfaceViewModel
import com.example.ageone.Models.Meditation

class MeditationViewModel: InterfaceViewModel {
    var model = MeditationModel()

    enum class EventType{
        OnEnterPressed,
        OnSearchPressed,
        OnMeditationPressed

    }

    val realmData = mutableListOf<Meditation>()

    fun loadRealmData() {
//        realmData = utils.realm.announce.getAllObjects().fil
    }

    fun initialize(recievedModel: InterfaceModel, completion: ()->(Unit)) {
        if (recievedModel is MeditationModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class MeditationModel: InterfaceModel {

}