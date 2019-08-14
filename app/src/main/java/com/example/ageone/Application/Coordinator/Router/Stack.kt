package com.example.ageone.Application.Coordinator.Router

import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator
import com.example.ageone.Application.Coordinator.Flow.Stack.runFlowMain
import com.example.ageone.Application.Coordinator.Flow.Stack.runFlowOne
import com.example.ageone.Application.Coordinator.Router.TabBar.Stack

fun FlowCoordinator.createStackFlows(startFlow: Int) {
    runFlowOne()
    runFlowMain()

    Stack.flows[startFlow].start()
}