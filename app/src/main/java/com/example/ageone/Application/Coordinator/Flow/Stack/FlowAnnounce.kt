package com.example.ageone.Application.Coordinator.Flow.Stack

import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.currentFlow
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.example.ageone.Application.Coordinator.Flow.setBottomNavigationVisible
import com.example.ageone.Application.Coordinator.Router.TabBar.Stack
import com.example.ageone.External.Base.Flow.BaseFlow
import com.example.ageone.External.Extensions.FlowCoordinator.DataFlow
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Modules.Announce.AnnounceView
import com.example.ageone.Modules.AnnounceViewModel

fun FlowCoordinator.runFlowAnnounce(settingsLastFlow: DataFlow) {

    var flow: FlowAnnounce? = FlowAnnounce(settingsLastFlow)

    flow?.let{ flow ->
        viewFlipperFlow.addView(flow.viewFlipperModule)
        viewFlipperFlow.displayedChild = viewFlipperFlow.indexOfChild(flow.viewFlipperModule)

        setBottomNavigationVisible(true)

        Stack.flows.add(flow)
    }

    flow?.onFinish = {
        viewFlipperFlow.removeView(flow?.viewFlipperModule)
        flow?.viewFlipperModule?.removeAllViews()
        flow = null
    }

//    flow?.start()
}

class FlowAnnounce(val settingsLastFlow: DataFlow): BaseFlow() {

    override fun start() {
        onStarted()
        currentFlow = this

        runModuleAnnounce()
    }

    fun runModuleAnnounce() {
        val module = AnnounceView(InitModuleUI())

        onBack = {
        }
        settingsCurrentFlow.isBottomBarVisible = true

        module.emitEvent = { event ->
            when (AnnounceViewModel.EventType.valueOf(event)) {

            }
        }
        push(module)
    }
}