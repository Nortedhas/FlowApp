package com.example.ageone.Application.Coordinator.Flow

import android.graphics.Color
import android.view.View
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.example.ageone.Application.AppActivity
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.viewFlipperFlowObject.viewFlipperFlow
import com.example.ageone.Application.Coordinator.Router.TabBar.TabBar.bottomNavigation
import com.example.ageone.Application.Coordinator.Router.TabBar.TabBar.createBottomNavigation
import com.example.ageone.Application.currentActivity
import com.example.ageone.Application.router
import com.example.ageone.Application.setStatusBarColor
import com.example.ageone.External.Base.Flow.BaseFlow
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.ViewFlipper.BaseViewFlipper
import yummypets.com.stevia.*

class FlowCoordinator {

    object stack {
        var flows = arrayListOf<BaseFlow>()
        var items = arrayListOf<AHBottomNavigationItem>()
    }

    fun setLaunchScreen() {

        router.initialize()
        renderUI()

        val launch = BaseModule()
        launch.setBackgroundColor(Color.TRANSPARENT)

        viewFlipperFlow.subviews(
            launch
        )

        launch.toolBar
            .height(0)

    }

    private var instructor = LaunchInstructor.configure()

    fun start() {
        when (instructor) {
            LaunchInstructor.Main -> runFlowMain()
            LaunchInstructor.Auth -> runFlowAuth()
        }

    }

    private fun renderUI() {
        createBottomNavigation()

        router.layout.subviews(
            viewFlipperFlow,
            bottomNavigation
        )

        viewFlipperFlow
            .fillVertically()
            .fillHorizontally()

        bottomNavigation.constrainBottomToBottomOf(router.layout)
        isBottomNavigationVisible(true)

    }

    object viewFlipperFlowObject{
        val viewFlipperFlow by lazy {
            val flipper = BaseViewFlipper()
            flipper
        }
    }




}

fun FlowCoordinator.isBottomNavigationVisible(visible: Boolean) = if (visible) {
    bottomNavigation.visibility = View.VISIBLE
} else {
    bottomNavigation.visibility = View.GONE
}

fun setStatusBarColor(color: Int) {
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

