package com.example.ageone.Modules.Meditation

import com.example.ageone.Application.utils
import com.example.ageone.External.Interfaces.InterfaceModel
import com.example.ageone.External.Interfaces.InterfaceViewModel
import com.example.ageone.External.RxBus.RxBus
import com.example.ageone.External.RxBus.RxEvent
import com.example.ageone.SCAG.Product
import io.reactivex.disposables.Disposable
import timber.log.Timber

class MeditationViewModel: InterfaceViewModel {
    var model = MeditationModel()

    enum class EventType{
        OnEnterPressed,
        OnSearchPressed,
        OnMeditationPressed

    }

    //TODO: перенести -> model
    var quickMeditation = listOf<Product>()

    private lateinit var personDisposable: Disposable


    fun loadRealmData() {

        quickMeditation = utils.realm.product.getAllObjects().filter { meditation ->
            meditation.isQuickStart
        }
        personDisposable = RxBus.listen(RxEvent.EventAddMeditation::class.java).subscribe { meditation ->
            Timber.i("Some event ${meditation.meditationName}")
        }
    }

    //TODO: where delete: if (!personDisposable.isDisposed) personDisposable.dispose()

    fun initialize(recievedModel: InterfaceModel, completion: ()->(Unit)) {
        if (recievedModel is MeditationModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class MeditationModel: InterfaceModel {

}