package com.example.ageone.Application

import android.app.Activity
import android.app.Application
import android.util.Log
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator
import com.example.ageone.Application.Coordinator.Router.Router
import com.example.ageone.External.Extensions.Application.FTActivityLifecycleCallbacks
import com.example.ageone.External.Libraries.Loger.TimberTree
import com.example.ageone.Internal.Utilities.Utils
import com.example.ageone.Models.User.user
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import net.alexandroid.shpref.ShPref
import timber.log.Timber
import android.R.attr.y
import android.R.attr.x
import android.graphics.Point
import android.view.Display
import io.realm.Realm

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

        user.isAuthorized = false

        // MARK: Timber
        if (BuildConfig.DEBUG) {
            val deviceManufacturer = android.os.Build.MANUFACTURER
            if (deviceManufacturer.toLowerCase().contains("huawei")) {
                Timber.plant(HuaweiTree())
            } else {
                Timber.plant(Timber.DebugTree())
            }
        }

        ReactiveNetwork
            .observeInternetConnectivity()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { isConnectedToInternet ->
//                    Timber.i("Internet are allowed")
                    utils.isNetworkReachable = isConnectedToInternet
            }

        Realm.init(this)

        registerActivityLifecycleCallbacks(mFTActivityLifecycleCallbacks)
    }

    companion object {

        var instance: App? = null

    }
}

class HuaweiTree : Timber.DebugTree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        var priority = priority
        if (priority == Log.VERBOSE || priority == Log.DEBUG)
            priority = Log.INFO
        super.log(priority, tag, message, t)
    }
}