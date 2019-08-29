package com.example.ageone.Application.Coordinator.Router

import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator
import com.example.ageone.Application.Coordinator.Flow.Stack.*
import com.example.ageone.Application.Coordinator.Router.TabBar.Stack
import com.example.ageone.External.Extensions.FlowCoordinator.DataFlow

fun FlowCoordinator.createStackFlows(startFlow: Int) {
    runFlowMain()
    runFlowSets()
    runFlowAnnounce()
    runFlowPurchases()
    runFlowProfile()

    Stack.flows[startFlow].start()
}