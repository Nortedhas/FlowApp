package com.example.ageone.Modules.Meditation

import com.example.ageone.Application.utils
import com.example.ageone.External.Interfaces.InterfaceModel
import com.example.ageone.External.Interfaces.InterfaceViewModel
import com.example.ageone.SCAG.Product

class MeditationViewModel: InterfaceViewModel {
    var model = MeditationModel()

    enum class EventType{
        OnEnterPressed,
        OnSearchPressed,
        OnMeditationPressed,
        OnPayed

    }

    var realmData = listOf<Product>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects().filter { meditation ->
            meditation.isQuickStart
        }
    }

    fun initialize(recievedModel: InterfaceModel, completion: ()->(Unit)) {
        if (recievedModel is MeditationModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class MeditationModel: InterfaceModel {
    var url = ""
}