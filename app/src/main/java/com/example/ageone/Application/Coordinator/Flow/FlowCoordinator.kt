package com.example.ageone.Application.Coordinator.Flow

import android.graphics.Color
import android.view.View
import android.view.WindowInsets
import android.widget.Button
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.example.ageone.Application.*
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.stack.items
import com.example.ageone.Application.R
import com.example.ageone.Application.R.color
import com.example.ageone.External.Base.Flow.BaseFlow
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.ViewFlipper.BaseViewFlipper
import com.example.ageone.Network.HTTP.Methods.handshake
import com.swarmnyc.promisekt.Promise
import io.socket.client.IO
import io.socket.client.Manager
import io.socket.client.Socket.EVENT_CONNECT
import io.socket.client.Socket.EVENT_DISCONNECT
import io.socket.emitter.Emitter
import io.socket.engineio.client.Transport
import timber.log.Timber
import yummypets.com.stevia.*


class FlowCoordinator {

    object stack {
        var flows = arrayListOf<BaseFlow>()
        var items = arrayListOf<AHBottomNavigationItem>()
    }

    fun setLaunchScreen() {

        router.initialize()
        renderUI()

        val launch = BaseModule(currentActivity)
        launch.setBackgroundColor(Color.TRANSPARENT)

        viewFlipperFlow.subviews(
            launch
        )

        launch.toolBar
            .height(0)

        Promise<Unit>{resolve,_ ->
                router.layout.setOnApplyWindowInsetsListener { _, insets ->
                    utils.variable.statusBarHeight = insets.systemWindowInsetTop
                    resolve(Unit)

                    insets
                }
        }.then {
            handshake()
        }.then {
            start()
        }

    }

    private var instructor = LaunchInstructor.configure()

    private fun start() {

        when (instructor) {
            LaunchInstructor.Main -> runFlowMain()
            LaunchInstructor.Auth -> runFlowAuth()
        }

    }

    private fun renderUI() {

        createStack()
        setUpTabs()

        router.layout.subviews(
            viewFlipperFlow,
            bottomNavigation
        )

        viewFlipperFlow
            .fillVertically()
            .fillHorizontally()

        bottomNavigation.constrainBottomToBottomOf(router.layout)
        isBottomNavigationVisible(false)

    }

    private fun setUpTabs() {

        for (item in items) {
            bottomNavigation.addItem(item)
        }

    }

    private fun createStack() {

        items = arrayListOf(
            AHBottomNavigationItem("One", R.drawable.abc_btn_check_material, R.color.material_blue_grey_800),
            AHBottomNavigationItem("Two", R.drawable.abc_btn_check_material, R.color.material_blue_grey_800)
        )

    }

    val viewFlipperFlow by lazy {
        val flipper = BaseViewFlipper()
        flipper
    }

    val bottomNavigation by lazy {
        val bottomNavigation = AHBottomNavigation(currentActivity)
        bottomNavigation.setTitleTextSize(30f,30f)
        bottomNavigation.defaultBackgroundColor = Color.parseColor("#FEFEFE")
        bottomNavigation.isBehaviorTranslationEnabled = true
        bottomNavigation.accentColor = Color.RED
        bottomNavigation.inactiveColor = Color.BLUE
        bottomNavigation.isForceTint = true
        bottomNavigation.isTranslucentNavigationEnabled = false
        bottomNavigation.titleState = AHBottomNavigation.TitleState.ALWAYS_SHOW
        bottomNavigation
    }

}

fun FlowCoordinator.isBottomNavigationVisible(visible: Boolean) = if (visible) {
    bottomNavigation.visibility = View.VISIBLE
} else {
    bottomNavigation.visibility = View.GONE
}

fun FlowCoordinator.setStatusBarColor(color: Int) {
    (currentActivity as AppActivity).setStatusBarColor(color)
}

private enum class LaunchInstructor {

    Main, Auth;

    companion object {

        fun configure(isAutorized: Boolean = true): LaunchInstructor {

            return when (isAutorized) {
                true -> Main
                false -> Auth
            }

        }

    }
}

