package com.example.ageone.Application.Coordinator.Flow

import android.graphics.Color
import android.view.View
import com.example.ageone.Application.AppActivity
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.example.ageone.Application.Coordinator.Router.TabBar.TabBar.bottomNavigation
import com.example.ageone.Application.Coordinator.Router.TabBar.TabBar.createBottomNavigation
import com.example.ageone.Application.Coordinator.Router.createStackFlows
import com.example.ageone.Application.currentActivity
import com.example.ageone.Application.router
import com.example.ageone.Application.setStatusBarColor
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.ViewFlipper.BaseViewFlipper
import yummypets.com.stevia.*

class FlowCoordinator {


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

        runFlowOne()

        /*when (instructor) {
            LaunchInstructor.Main -> runFlowMain()
            LaunchInstructor.Auth -> runFlowAuth()
        }*/

        /*createStackFlows()
        createBottomNavigation()

        bottomNavigation.constrainBottomToBottomOf(router.layout)
        bottomNavigation.currentItem = 1
        setBottomNavigationVisible(true)*/
    }

    private fun renderUI() {
        router.layout.subviews(
            viewFlipperFlow,
            bottomNavigation
        )

        viewFlipperFlow
            .fillVertically()
            .fillHorizontally()

    }


    object ViewFlipperFlowObject{
        val viewFlipperFlow by lazy {
            val flipper = BaseViewFlipper()
            flipper
        }
    }
}

fun setBottomNavigationVisible(visible: Boolean) = if (visible) {
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

        fun configure(isAutorized: Boolean = false): LaunchInstructor {

            return when (isAutorized) {
                true -> Main
                false -> Auth
            }

        }

    }
}

