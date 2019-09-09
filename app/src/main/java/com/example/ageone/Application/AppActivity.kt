package com.example.ageone.Application

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.ageone.Application.Coordinator.Flow.FlowAuth
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.currentFlow
import com.example.ageone.External.Base.Activity.BaseActivity
import com.example.ageone.External.HTTP.API.API
import com.example.ageone.External.Libraries.Alert.alertManager
import com.example.ageone.External.Libraries.Alert.single
import com.example.ageone.Models.User.user
import com.example.ageone.SCAG.DataBase
import com.github.kittinunf.fuel.core.FuelManager
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.swarmnyc.promisekt.Promise
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import com.vk.api.sdk.exceptions.VKApiExecutionException
import timber.log.Timber
import com.vk.api.sdk.requests.VKRequest
import org.intellij.lang.annotations.Flow
import org.json.JSONObject
import java.lang.Exception


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


//        VKSdk.initialize(Context applicationContext)

        FuelManager.instance.basePath = DataBase.url

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val callback = object: VKAuthCallback {
            override fun onLogin(token: VKAccessToken) {
                // User passed authorization
                Timber.i("Token: ${token.accessToken}")
                utils.variable.vkSdkTokenUser = token.accessToken
                Timber.i("${VKUsersRequest()}")
                VK.execute(VKUsersRequest(), object: VKApiCallback<VKUser> {
                    override fun success(result: VKUser) {
                        Timber.i("Result $result")
                        user.data.email = token.email ?: ""
                        user.data.fullName = "${result.firstName} ${result.lastName}"
                        if (currentFlow is FlowAuth) {
                            (currentFlow as FlowAuth).runModuleRegistration()
                        }
                    }
                    override fun fail(error: VKApiExecutionException) {
                    }
                })

            }

            override fun onLoginFailed(errorCode: Int) {
                // User didn't pass authorization
                alertManager.single("Ошибка авторизации", "Ошибка авторизации через vk", null) { _, _ ->
                }
            }
        }
        if (data == null || !VK.onActivityResult(requestCode, resultCode, data, callback)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
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

class VKUsersRequest: VKRequest<VKUser> {
    constructor(): super("account.getProfileInfo") {
        addParam("access_token", utils.variable.vkSdkTokenUser)
    }

    override fun parse(r: JSONObject): VKUser {
        var result = VKUser()
        try {
             result = VKUser.parse(r.getJSONObject("response"))
        }
        catch (e: Exception) {
            Timber.e("Error parsing VK user: $e")
        }
        return result
    }
}