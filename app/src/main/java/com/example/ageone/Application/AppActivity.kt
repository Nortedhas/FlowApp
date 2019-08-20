package com.example.ageone.Application

import android.graphics.Point
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import com.example.ageone.External.Base.Activity.BaseActivity
import com.example.ageone.Network.HTTP.Methods
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.swarmnyc.promisekt.Promise
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import yummypets.com.stevia.dp


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

    private fun setDisplaySize(){
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        utils.variable.displayWidth = size.x / 3
        utils.variable.displayHeight = size.y / 3

        // Calculate ActionBar height
        val tv = TypedValue()
        if (theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            utils.variable.actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, resources.displayMetrics) / 3
        }

        Timber.i("height: ${utils.variable.actionBarHeight}")
    }
}



fun AppActivity.setStatusBarColor(color: Int) {
    window.statusBarColor = color
}