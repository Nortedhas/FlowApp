package com.example.ageone.External.Extensions.FlowCoordinator

import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.example.ageone.Application.Coordinator.Flow.setBottomNavigationVisible
import com.example.ageone.External.Base.Flow.BaseFlow

data class DataFlow(
    val indexOnFlipperFlow: Int = 0,
    var isBottomBarVisible: Boolean = false
)

fun FlowCoordinator.pop(settingsLastFlow: DataFlow){
    viewFlipperFlow.displayedChild = settingsLastFlow.indexOnFlipperFlow
    setBottomNavigationVisible(settingsLastFlow.isBottomBarVisible)
}