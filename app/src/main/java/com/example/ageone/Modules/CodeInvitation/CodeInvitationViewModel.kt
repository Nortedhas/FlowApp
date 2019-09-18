package com.example.ageone.Modules.CodeInvitation

import com.example.ageone.External.Interfaces.InterfaceModel
import com.example.ageone.External.Interfaces.InterfaceViewModel

class CodeInvitationViewModel : InterfaceViewModel {
    var model = CodeInvitationModel()

    enum class EventType {

    }

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is CodeInvitationModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class CodeInvitationModel : InterfaceModel {

}
