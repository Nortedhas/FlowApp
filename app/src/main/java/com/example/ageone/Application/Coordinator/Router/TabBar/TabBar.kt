package com.example.ageone.Application.Coordinator.Router.TabBar

import android.graphics.Color
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.example.ageone.Application.Coordinator.Flow.FlowAuth
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.viewFlipperFlowObject.viewFlipperFlow
import com.example.ageone.Application.Coordinator.Flow.FlowMain
import com.example.ageone.Application.R
import com.example.ageone.Application.currentActivity

object TabBar {

    fun createBottomNavigation() {
        bottomNavigation.setOnTabSelectedListener { position, wasSelected ->
            if (!wasSelected) {
                viewFlipperFlow.displayedChild = position
//                router.setCurrentFLow(flows[position])
            }
            true
        }

        createStack()
        setUpTabs()
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

    private fun setUpTabs() {

        for (item in FlowCoordinator.stack.items) {
            bottomNavigation.addItem(item)
        }

        for (flow in FlowCoordinator.stack.flows) {
            viewFlipperFlow.addView(flow)
        }

    }

    private fun createStack() {

        FlowCoordinator.stack.items = arrayListOf(
            AHBottomNavigationItem("Auth", R.drawable.abc_btn_check_material, R.color.material_blue_grey_800),
            AHBottomNavigationItem("Main", R.drawable.abc_btn_check_material, R.color.material_blue_grey_800)
        )

        FlowCoordinator.stack.flows = arrayListOf(
            flowAuth,
            flowMain
        )

    }
}

