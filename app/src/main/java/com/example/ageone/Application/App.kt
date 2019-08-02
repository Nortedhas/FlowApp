package com.example.ageone.Application

import android.app.Activity
import android.app.Application
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator
import com.example.ageone.Application.Coordinator.Router.Router
import com.example.ageone.External.Libraries.Loger.TimberTree
import com.example.ageone.Internal.Utilities.Utils
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import net.alexandroid.shpref.ShPref
import timber.log.Timber

val router = Router()
val coordinator = FlowCoordinator()

val utils = Utils()



val currentActivity: Activity?
    get() = App.instance?.mFTActivityLifecycleCallbacks?.currentActivity

class App: Application()  {

    init {
        instance = this
    }

    val mFTActivityLifecycleCallbacks = FTActivityLifecycleCallbacks()

    override fun onCreate() {
        super.onCreate()

        // MARK: SharePreferences

        ShPref.init(this, ShPref.APPLY)

        // MARK: Timber

        Timber.plant(TimberTree())

        ReactiveNetwork
            .observeInternetConnectivity()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { isConnectedToInternet ->
                // do something with isConnectedToInternet value
                if (isConnectedToInternet != utils.isNetworkReachable) {
//                    Timber.i("Internet are allowed")
                    utils.isNetworkReachable = isConnectedToInternet
                }
            }

        registerActivityLifecycleCallbacks(mFTActivityLifecycleCallbacks)
    }

    companion object {

        var instance: App? = null

    }
}