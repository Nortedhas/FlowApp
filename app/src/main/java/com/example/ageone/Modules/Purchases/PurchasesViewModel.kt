package com.example.ageone.Modules

import com.example.ageone.External.Interfaces.InterfaceModel
import com.example.ageone.External.Interfaces.InterfaceViewModel

class PurchasesViewModel : InterfaceViewModel {
    var model = PurchasesModel()

    fun initialize(recievedModel: InterfaceModel, completion: ()->(Unit)) {
        if (recievedModel is PurchasesModel) {
            model = recievedModel
            completion.invoke()
        }
    }

    enum class EventType {
        OnSetPressed
    }
}

class PurchasesModel : InterfaceModel {

}
