package com.example.ageone.Application.Coordinator.Router

import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator
import com.example.ageone.Application.Coordinator.Flow.runFlowAuth
import com.example.ageone.Application.Coordinator.Flow.runFlowMain

fun FlowCoordinator.createStack() {
    runFlowAuth()
    runFlowMain()
}