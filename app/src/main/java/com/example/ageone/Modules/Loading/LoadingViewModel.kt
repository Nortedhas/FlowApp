package com.example.ageone.Modules

import com.example.ageone.Application.api
import com.example.ageone.External.Interfaces.InterfaceModel
import com.example.ageone.External.Interfaces.InterfaceViewModel
import com.example.ageone.Modules.Meditation.MeditationModel
import timber.log.Timber

class LoadingViewModel : InterfaceViewModel {
    var model = LoadingModel()

    enum class EventType{
        onFinish
    }

    fun initialize(recievedModel: InterfaceModel, completion: ()->(Unit)) {
        if (recievedModel is LoadingModel) {
            model = recievedModel
            completion.invoke()
        }
    }

    fun startLoading(completion: () -> Unit) {
        api.requestMainLoad().done {
            Timber.i("completion invoke")
            completion.invoke()
        }
    }
}

class LoadingModel : InterfaceModel {

}
