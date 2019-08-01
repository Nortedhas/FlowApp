package com.example.ageone.Application.Coordinator.Flow

import android.graphics.Color
import android.view.View
import android.widget.ViewFlipper
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.stack.items
import com.example.ageone.Application.currentActivity
import com.example.ageone.Application.router
import com.example.ageone.Application.utils
import com.example.ageone.External.Base.Flow.BaseFlow
import com.example.ageone.External.Base.Module.BaseModule
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

        router.layout.setOnApplyWindowInsetsListener { _, insets ->
            utils.variable.statusBarHeight = insets.systemWindowInsetTop
//            router.layout.setPadding(0, insets.systemWindowInsetTop, 0, 0)

            start()
            insets
        }

    }



    private var instructor = LaunchInstructor.configure()

    private fun start() {

        when (instructor) {
            LaunchInstructor.Main -> print("")
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

    val viewFlipperFlow: ViewFlipper by lazy {
        val flipper = ViewFlipper(currentActivity)
        flipper
    }

    val bottomNavigation: AHBottomNavigation by lazy {
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

