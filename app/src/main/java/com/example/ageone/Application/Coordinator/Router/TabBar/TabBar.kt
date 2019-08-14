package com.example.ageone.Application.Coordinator.Router.TabBar

import android.graphics.Color
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.example.ageone.Application.Coordinator.Flow.setStatusBarColor
import com.example.ageone.Application.Coordinator.Router.TabBar.Stack.flows
import com.example.ageone.Application.Coordinator.Router.TabBar.Stack.items
import com.example.ageone.Application.R
import com.example.ageone.Application.currentActivity
import com.example.ageone.Application.router
import com.example.ageone.External.Base.Flow.BaseFlow

object Stack {
    var flows = arrayListOf<BaseFlow>()
    var items = arrayListOf<AHBottomNavigationItem>()
}

object TabBar {

    fun createBottomNavigation() {

        bottomNavigation.setOnTabSelectedListener { position, wasSelected ->
            if (!wasSelected) {
                viewFlipperFlow.displayedChild = position

                if (!flows[position].isStarted) {
                    flows[position].start()
                }

                setStatusBarColor(flows[position].colorStatusBar)
            }
            true
        }

        createStackItem()
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

    private fun setUpTabs() {

        for (item in items) {
            bottomNavigation.addItem(item)
        }

//        viewFlipperFlow.removeAllViews()

        for (flow in flows) {
            viewFlipperFlow.addView(flow)
        }

//        flows[0].start()

    }

    private fun createStackItem() {
        items = arrayListOf(
            AHBottomNavigationItem("One", R.drawable.abc_btn_check_material, R.color.material_blue_grey_800),
            AHBottomNavigationItem("Main", R.drawable.abc_btn_check_material, R.color.material_blue_grey_800)
        )

    }
}

