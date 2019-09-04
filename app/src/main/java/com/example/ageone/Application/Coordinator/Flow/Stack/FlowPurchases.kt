package com.example.ageone.Application.Coordinator.Flow.Stack

import androidx.core.view.size
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.example.ageone.Application.Coordinator.Flow.Regular.runFlowPleer
import com.example.ageone.Application.Coordinator.Router.DataFlow
import com.example.ageone.Application.Coordinator.Router.TabBar.Stack
import com.example.ageone.Application.coordinator
import com.example.ageone.External.Base.Flow.BaseFlow
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Modules.Purchases.PurchasesView
import com.example.ageone.Modules.PurchasesViewModel
import com.example.ageone.Modules.SetsIn.SetsInView
import com.example.ageone.Modules.SetsInViewModel

fun FlowCoordinator.runFlowPurchases() {

    var flow: FlowPurchases? = FlowPurchases()

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

class FlowPurchases: BaseFlow() {

    override fun start() {
        onStarted()
        runModulePurchases()
    }

    fun runModulePurchases() {
        val module = PurchasesView(InitModuleUI())

        settingsCurrentFlow.isBottomNavigationVisible = true

        module.emitEvent = { event ->
            when (PurchasesViewModel.EventType.valueOf(event)) {
                PurchasesViewModel.EventType.OnSetPressed -> {
                    runModuleSetsIn()
                }
            }
        }
        push(module)
    }

    fun runModuleSetsIn() {
        val module = SetsInView(InitModuleUI(
            isBottomNavigationVisible = false,
            isBackPressed = true,
            backListener = {
                pop()
            }
        ))

        settingsCurrentFlow.isBottomNavigationVisible = false

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