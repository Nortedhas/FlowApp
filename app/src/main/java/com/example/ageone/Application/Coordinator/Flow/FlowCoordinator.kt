package com.example.ageone.Application.Coordinator.Flow

import android.graphics.Color
import android.view.View
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.example.ageone.Application.*
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.stack.flows
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.stack.items
import com.example.ageone.Application.R
import com.example.ageone.External.Base.Flow.BaseFlow
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.ViewFlipper.BaseViewFlipper
import com.example.ageone.Network.HTTP.Methods
import com.example.ageone.Network.HTTP.Methods.handshake
import com.swarmnyc.promisekt.Promise
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

        Timber.i("1")


        val launch = BaseModule()
        Timber.i("2")
        launch.setBackgroundColor(Color.TRANSPARENT)
        Timber.i("3")

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

        createStack()
        setUpTabs()
    }

    private fun renderUI() {

        bottomNavigation.setOnTabSelectedListener { position, wasSelected ->
            if (!wasSelected) {
                viewFlipperFlow.displayedChild = position
//                router.setCurrentFLow(flows[position])
            }
            true
        }

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

        for (flow in flows) {
            viewFlipperFlow.addView(flow)
        }

    }

    private fun createStack() {

        items = arrayListOf(
            AHBottomNavigationItem("Auth", R.drawable.abc_btn_check_material, R.color.material_blue_grey_800),
            AHBottomNavigationItem("Main", R.drawable.abc_btn_check_material, R.color.material_blue_grey_800)
        )

        flows = arrayListOf(
            flowAuth,
            flowMain
        )

    }

    val flowMain by lazy {
        val flowMain = FlowMain()
//        runFlowMain()
        flowMain
    }

    val flowAuth by lazy {
        val flowAuth = FlowAuth()
//        runFlowAuth()
        flowAuth
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

