package com.example.ageone.Modules.Purchases

import com.example.ageone.Application.utils
import com.example.ageone.External.Extensions.Realm.getAllMeditations
import com.example.ageone.External.Extensions.Realm.getAllSets
import com.example.ageone.External.Interfaces.InterfaceModel
import com.example.ageone.External.Interfaces.InterfaceViewModel
import com.example.ageone.SCAG.Order

class PurchasesViewModel : InterfaceViewModel {
    var model = PurchasesModel()

    enum class EventType {
        OnSetPressed,
        OnMeditationPressed;
    }

    fun initialize(recievedModel: InterfaceModel, completion: ()->(Unit)) {
        if (recievedModel is PurchasesModel) {
            model = recievedModel
            completion.invoke()
        }
    }

    fun loadRealmData() {
        model.meditations = utils.realm.order.getAllMeditations()
        model.sets = utils.realm.order.getAllSets()
    }
}

class PurchasesModel : InterfaceModel {
    var meditations = listOf<Order>()
    var sets = listOf<Order>()
}
