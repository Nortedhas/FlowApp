package com.example.ageone.Application.Coordinator.Router

import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator
import com.example.ageone.Application.Coordinator.Flow.runFlowMain
import com.example.ageone.Application.Coordinator.Flow.runFlowOne

fun FlowCoordinator.createStackFlows() {
    runFlowOne()
    runFlowMain()
}