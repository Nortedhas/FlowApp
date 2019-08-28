package com.example.ageone.Application.Coordinator.Flow.Stack

import androidx.core.view.size
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.example.ageone.Application.Coordinator.Flow.setBottomNavigationVisible
import com.example.ageone.Application.Coordinator.Router.TabBar.Stack
import com.example.ageone.Application.coordinator
import com.example.ageone.External.Base.Flow.BaseFlow
import com.example.ageone.External.Extensions.FlowCoordinator.DataFlow
import com.example.ageone.External.Extensions.FlowCoordinator.pop
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Modules.Purchases.PurchasesView
import com.example.ageone.Modules.PurchasesViewModel

fun FlowCoordinator.runFlowPurchases(settingsLastFlow: DataFlow) {

    var flow: FlowPurchases? = FlowPurchases(settingsLastFlow)

    flow?.let{ flow ->
        viewFlipperFlow.addView(flow.viewFlipperModule)
        viewFlipperFlow.displayedChild = viewFlipperFlow.indexOfChild(flow.viewFlipperModule)

        flow.settingsCurrentFlow = DataFlow(viewFlipperFlow.size - 1)

        Stack.flows.add(flow)
    }

    flow?.onFinish = {
        FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow.removeView(flow?.viewFlipperModule)
        flow?.viewFlipperModule?.removeAllViews()
        flow = null
    }

//    flow?.start()
}

class FlowPurchases(val settingsLastFlow: DataFlow): BaseFlow() {

    override fun start() {
        onStarted()
        FlowCoordinator.ViewFlipperFlowObject.currentFlow = this
        runModulePurchases()
    }

    fun runModulePurchases() {
        val module = PurchasesView(InitModuleUI())

        onBack = {
            coordinator.pop(settingsLastFlow)
        }

        module.emitEvent = { event ->
            when (PurchasesViewModel.EventType.valueOf(event)) {

            }
        }
        push(module)
    }
}