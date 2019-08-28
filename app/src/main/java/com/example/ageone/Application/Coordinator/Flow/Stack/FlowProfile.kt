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
import com.example.ageone.Modules.Profile.ProfileView
import com.example.ageone.Modules.ProfileViewModel
import com.example.ageone.Modules.ProfileVip.ProfileVipView
import com.example.ageone.Modules.ProfileVipViewModel

fun FlowCoordinator.runFlowProfile(settingsLastFlow: DataFlow) {

    var flow: FlowProfile? = FlowProfile(settingsLastFlow)

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

class FlowProfile(val settingsLastFlow: DataFlow): BaseFlow() {

    override fun start() {
        onStarted()
        FlowCoordinator.ViewFlipperFlowObject.currentFlow = this
        runModuleProfile()
    }

    fun runModuleProfile() {
        val module = ProfileView()

        onBack = {
            coordinator.pop(settingsLastFlow)
        }

        module.emitEvent = { event ->
            when (ProfileViewModel.EventType.valueOf(event)) {
                ProfileViewModel.EventType.OnGetVipPressed -> {
                    runModuleProfileVip()
                }
            }
        }
        push(module)
    }

    fun runModuleProfileVip() {
        val module = ProfileVipView(InitModuleUI(
            isBottomNavigationVisible = false,
            backListener = {
                pop()
            }
        ))

        module.emitEvent = { event ->
            when (ProfileVipViewModel.EventType.valueOf(event)) {

            }
        }
        push(module)
    }
}