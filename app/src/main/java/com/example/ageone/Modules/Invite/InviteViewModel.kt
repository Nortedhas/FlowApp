package com.example.ageone.Modules.Invite

import com.example.ageone.External.Interfaces.InterfaceModel
import com.example.ageone.External.Interfaces.InterfaceViewModel

class InviteViewModel: InterfaceViewModel {
    var model = InviteModel()

    enum class EventType{

    }

    fun loadRealmData() {

    }

    fun initialize(recievedModel: InterfaceModel, completion: ()->(Unit)) {
        if (recievedModel is InviteModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class InviteModel: InterfaceModel {
}