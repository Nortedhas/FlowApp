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
import com.example.ageone.Modules.SetsIn.SetsInView
import com.example.ageone.Modules.SetsInViewModel

fun FlowCoordinator.runFlowSet(previousFlow: BaseFlow) {

    var flow: FlowSet? = FlowSet(previousFlow)

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

class FlowSet(previousFlow: BaseFlow? = null): BaseFlow() {

    init {
        this.previousFlow = previousFlow
    }

    override fun start() {
        onStarted()
        currentFlow = this
        runModuleSetsIn()
    }

    fun runModuleSetsIn() {
        val module = SetsInView(InitModuleUI(
            backListener = {
                coordinator.pop(previousFlow)
            }
        ))

        settingsCurrentFlow.isBottomBarVisible = true

        module.emitEvent = { event ->
            when (SetsInViewModel.EventType.valueOf(event)) {
                SetsInViewModel.EventType.OnMeditationPressed -> {
                    coordinator.runFlowPleer(this)
                }
            }
        }
        push(module)
    }
}