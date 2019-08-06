package com.example.ageone.Application.Coordinator.Flow

import android.app.Activity
import android.graphics.Color
import android.view.View
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.bumptech.glide.Priority
import com.bumptech.glide.request.RequestOptions
import com.example.ageone.Application.*
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.example.ageone.Application.Coordinator.Router.TabBar.TabBar.bottomNavigation
import com.example.ageone.Application.Coordinator.Router.TabBar.TabBar.createBottomNavigation
import com.example.ageone.External.Base.Flow.BaseFlow
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.ViewFlipper.BaseViewFlipper
import com.example.ageone.External.Libraries.Glide.GlideApp
import com.example.ageone.Models.User.UserData
import com.swarmnyc.promisekt.Promise.Companion.resolve
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

        val launch = BaseModule()
        launch.setBackgroundColor(Color.TRANSPARENT)

        viewFlipperFlow.subviews(
            launch
        )

        launch.toolBar
            .height(0)

        router.layout.setOnApplyWindowInsetsListener { _, insets ->
            utils.variable.statusBarHeight = insets.systemWindowInsetTop
            resolve(Unit)
            insets
        }

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
        setBottomNavigationVisible(true)

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

