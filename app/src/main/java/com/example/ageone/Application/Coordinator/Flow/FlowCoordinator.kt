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
import com.example.ageone.External.Base.Flow.BaseFlow
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.ViewFlipper.BaseViewFlipper
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Models.User.user
import yummypets.com.stevia.*

var isBottomNavigationExist = true

class FlowCoordinator {
    fun setLaunchScreen() {

        router.initialize()
        renderUI()

        val launch = BaseModule(InitModuleUI(colorToolbar = Color.TRANSPARENT))
        launch.setBackgroundColor(Color.TRANSPARENT)

        viewFlipperFlow.subviews(
            launch
        )

        launch.toolbar
            .height(0)

    }

    fun start() {

        viewFlipperFlow.removeAllViews()

        when (LaunchInstructor.configure()) {
            LaunchInstructor.Main -> {
                val startFlow = 0
                createStackFlows(startFlow)
                createBottomNavigation()

                bottomNavigation.currentItem = startFlow
                viewFlipperFlow.displayedChild = startFlow

            }
            LaunchInstructor.Auth -> runFlowAuth()
        }
    }

    private fun renderUI() {

        router.layout.removeAllViews()
        router.layout.subviews(
            viewFlipperFlow,
            bottomNavigation
        )

        bottomNavigation.constrainBottomToBottomOf(router.layout)

        viewFlipperFlow
            .fillVertically()
            .fillHorizontally()
            .constrainBottomToTopOf(bottomNavigation)
    }

    object ViewFlipperFlowObject{
        var currentFlow: BaseFlow? = null

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

        fun configure(isAutorized: Boolean = user.isAuthorized): LaunchInstructor {
            return when (isAutorized) {
                true -> Main
                false -> Auth
            }

        }

    }
}

