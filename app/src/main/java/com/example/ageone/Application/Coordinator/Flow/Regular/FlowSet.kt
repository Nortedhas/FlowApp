package com.example.ageone.Application.Coordinator.Flow.Regular

import androidx.core.view.size
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.example.ageone.Application.Coordinator.Router.TabBar.Stack
import com.example.ageone.Application.coordinator
import com.example.ageone.External.Base.Flow.BaseFlow
import com.example.ageone.External.Extensions.FlowCoordinator.DataFlow
import com.example.ageone.External.Extensions.FlowCoordinator.pop
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Modules.SetsIn.SetsInView
import com.example.ageone.Modules.SetsInViewModel

fun FlowCoordinator.runFlowSet(settingsLastFlow: DataFlow) {

    var flow: FlowSet? = FlowSet(settingsLastFlow)

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

class FlowSet(val settingsLastFlow: DataFlow): BaseFlow() {

    override fun start() {
        onStarted()
        FlowCoordinator.ViewFlipperFlowObject.currentFlow = this
        runModuleSetsIn()
    }

    fun runModuleSetsIn() {
        val module = SetsInView(InitModuleUI(
            backListener = {
                coordinator.pop(settingsLastFlow)
            }
        ))

        onBack = {
            coordinator.pop(settingsLastFlow)
        }

        module.emitEvent = { event ->
            when (SetsInViewModel.EventType.valueOf(event)) {

            }
        }
        push(module)
    }
}