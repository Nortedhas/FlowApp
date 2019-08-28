package com.example.ageone.Application.Coordinator.Router

import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator
import com.example.ageone.Application.Coordinator.Flow.Stack.*
import com.example.ageone.Application.Coordinator.Router.TabBar.Stack
import com.example.ageone.External.Extensions.FlowCoordinator.DataFlow

fun FlowCoordinator.createStackFlows(startFlow: Int) {
    runFlowMain(DataFlow(isBottomBarVisible = true))
    runFlowSets(DataFlow(isBottomBarVisible = true))
    runFlowAnnounce(DataFlow(isBottomBarVisible = true))
    runFlowPurchases(DataFlow(isBottomBarVisible = true))
    runFlowProfile(DataFlow(isBottomBarVisible = true))

    Stack.flows[startFlow].start()
}