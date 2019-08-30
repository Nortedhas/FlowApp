package com.example.ageone.Application.Coordinator.Flow.Stack

import androidx.core.view.size
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.currentFlow
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.example.ageone.Application.Coordinator.Flow.setBottomNavigationVisible
import com.example.ageone.Application.Coordinator.Router.DataFlow
import com.example.ageone.Application.Coordinator.Router.TabBar.Stack
import com.example.ageone.External.Base.Flow.BaseFlow
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Modules.Announce.AnnounceView
import com.example.ageone.Modules.AnnounceViewModel

fun FlowCoordinator.runFlowAnnounce() {

    var flow: FlowAnnounce? = FlowAnnounce()

    flow?.let{ flow ->
        viewFlipperFlow.addView(flow.viewFlipperModule)
        viewFlipperFlow.displayedChild = viewFlipperFlow.indexOfChild(flow.viewFlipperModule)

        flow.settingsCurrentFlow = DataFlow(viewFlipperFlow.size - 1)

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

class FlowAnnounce: BaseFlow() {

    override fun start() {
        onStarted()
        runModuleAnnounce()
    }

    fun runModuleAnnounce() {
        val module = AnnounceView(InitModuleUI())

        settingsCurrentFlow.isBottomNavigationVisible = true

        module.emitEvent = { event ->
            when (AnnounceViewModel.EventType.valueOf(event)) {

            }
        }
        push(module)
    }
}