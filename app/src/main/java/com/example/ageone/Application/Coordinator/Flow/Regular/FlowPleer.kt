package com.example.ageone.Application.Coordinator.Flow.Regular

import androidx.core.view.size
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.currentFlow
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.example.ageone.Application.Coordinator.Router.TabBar.Stack
import com.example.ageone.Application.coordinator
import com.example.ageone.External.Base.Flow.BaseFlow
import com.example.ageone.External.Extensions.FlowCoordinator.DataFlow
import com.example.ageone.External.Extensions.FlowCoordinator.pop
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Modules.Pleer.PleerView
import com.example.ageone.Modules.PleerViewModel

fun FlowCoordinator.runFlowPleer(settingsLastFlow: DataFlow) {

    var flow: FlowPleer? = FlowPleer(settingsLastFlow)

    flow?.let{ flow ->
        viewFlipperFlow.addView(flow.viewFlipperModule)
        viewFlipperFlow.displayedChild = viewFlipperFlow.indexOfChild(flow.viewFlipperModule)
        flow.settingsCurrentFlow = DataFlow(viewFlipperFlow.size - 1)

        Stack.flows.add(flow)
    }

    flow?.onFinish = {
        viewFlipperFlow.removeView(flow?.viewFlipperModule)
        flow?.viewFlipperModule?.removeAllViews()
        flow = null
    }

    flow?.start()
}

class FlowPleer(val settingsLastFlow: DataFlow): BaseFlow() {

    override fun start() {
        onStarted()
        currentFlow = this
        runModulePleer()
    }

    fun runModulePleer() {
        val module = PleerView(InitModuleUI(
            isBottomNavigationVisible = false,
            backListener = {
                coordinator.pop(settingsLastFlow)
            }
        ))

        onBack = {
            coordinator.pop(settingsLastFlow)
        }
        settingsCurrentFlow.isBottomBarVisible = false

        module.emitEvent = { event ->
            when (PleerViewModel.EventType.valueOf(event)) {

            }
        }
        push(module)
    }
}