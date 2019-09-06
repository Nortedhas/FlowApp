package com.example.ageone.Application

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.ageone.External.Base.Activity.BaseActivity
import com.example.ageone.External.HTTP.API.API
import com.github.kittinunf.fuel.core.FuelManager
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.swarmnyc.promisekt.Promise
import timber.log.Timber

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject



class AppActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        //only vertical mode
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        // for launchScreen
        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)

        //fullscreen flags
        window.decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        setDisplaySize()

        FuelManager.instance.basePath = "http://45.141.102.83"

        //TODO just do this
        coordinator.setLaunchScreen()
        Promise<Unit> { resolve, _ ->

            router.layout.setOnApplyWindowInsetsListener { _, insets ->
                utils.variable.statusBarHeight = insets.systemWindowInsetTop

                resolve(Unit)
                insets
            }

        }.then {
            API().handshake().then {
                    API().requestMainLoad()
            }
        }.then {
            coordinator.start()
        }

        setContentView(router.layout)

        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Timber.i("fail")
                    return@OnCompleteListener
                }

                // Get new Instance ID UserHandshake
                val token = task.result?.token
//                Timber.i("$UserHandshake")
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
        router.onBackPressed()
    }

}

fun Activity.hideKeyboard() {
    // Check if no view has
    val view = currentFocus
    view?.let { v ->
        //hide keyboard
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(v.windowToken, 0)
    }
}

fun AppActivity.setStatusBarColor(color: Int) {
    window.statusBarColor = color
}