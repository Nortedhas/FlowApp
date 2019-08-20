package com.example.ageone.Application.Coordinator.Router

import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator
import com.example.ageone.Application.Coordinator.Flow.Stack.runFlowMain
import com.example.ageone.Application.Coordinator.Flow.Stack.runFlowSets
import com.example.ageone.Application.Coordinator.Router.TabBar.Stack

fun FlowCoordinator.createStackFlows(startFlow: Int) {
    runFlowMain()
    runFlowSets()

    Stack.flows[startFlow].start()
}