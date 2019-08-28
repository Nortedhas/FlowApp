package com.example.ageone.Application

import android.graphics.Point
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.example.ageone.External.Base.Activity.BaseActivity
import com.example.ageone.External.Base.Flow.BaseFlow
import com.example.ageone.Network.HTTP.Methods
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.swarmnyc.promisekt.Promise
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import android.util.DisplayMetrics
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.currentFlow


class AppActivity: BaseActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {

        // for launchScreen
        setTheme(R.style.AppTheme)

        savedInstanceState?.let {
            Timber.i("reload")
        }
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        setDisplaySize()

        coordinator.setLaunchScreen()
        Promise<Unit> { resolve, _ ->

            router.layout.setOnApplyWindowInsetsListener { _, insets ->
                utils.variable.statusBarHeight = insets.systemWindowInsetTop

                resolve(Unit)
                insets
            }

        }.then {
            Methods.handshake()
        }.then {
            coordinator.start()
        }

        setContentView(router.layout)

        val getDb = Apifactory.curApi.getDb(
            DbBody(
                "fetch",
                "Tariff",
                ""
            )
        )

        getDb.enqueue(object : Callback<DbResponse>{
            override fun onFailure(call: Call<DbResponse>, t: Throwable) {
                Timber.i("Fail")
            }

            override fun onResponse(call: Call<DbResponse>, response: Response<DbResponse>) {
                Timber.i("${response.body()}")

                if (response.isSuccessful) {
                    Timber.i("ok")
                }
            }

        })

        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Timber.i("fail")
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = task.result?.token
//                Timber.i("$token")
            })
    }

    private fun setDisplaySize() {
        val displayMetrics = resources.displayMetrics
        utils.variable.displayWidth = (displayMetrics.widthPixels / displayMetrics.density).toInt()
        utils.variable.displayHeight = (displayMetrics.heightPixels / displayMetrics.density).toInt()

        Timber.i("width = ${utils.variable.displayWidth}")

        // Calculate ActionBar height
        val tv = TypedValue()
        if (theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            utils.variable.actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, resources.displayMetrics) / 3
        }
    }

    override fun onBackPressed() {
        Timber.i("back")
        currentFlow?.onBack?.invoke()
    }
}



fun AppActivity.setStatusBarColor(color: Int) {
    window.statusBarColor = color
}