package com.example.ageone.Application.Coordinator.Router

import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator
import com.example.ageone.Application.Coordinator.Flow.Stack.*
import com.example.ageone.Application.Coordinator.Router.TabBar.Stack

fun FlowCoordinator.createStackFlows(startFlow: Int) {

    // MARK: in order like in navigation
    Stack.flows.clear()

    runFlowMain()
    runFlowSets()
    runFlowAnnounce()
    runFlowPurchases()
    runFlowProfile()

    Stack.flows[startFlow].start()
}