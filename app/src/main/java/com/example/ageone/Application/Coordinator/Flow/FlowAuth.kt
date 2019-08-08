package com.example.ageone.Application.Coordinator.Flow

import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.example.ageone.Application.Coordinator.Router.TabBar.Stack.flows
import com.example.ageone.External.Base.Flow.BaseFlow
import com.example.ageone.Modules.Auth.AuthView
import com.example.ageone.Modules.Auth.AuthViewModel
import com.example.ageone.Modules.Password.PasswordView
import com.example.ageone.Modules.Password.PasswordViewModel

fun FlowCoordinator.runFlowAuth() {

    var flow: FlowAuth? = FlowAuth()

    flow?.let{ flow ->

        viewFlipperFlow.addView(flow.viewFlipperModule)
        viewFlipperFlow.displayedChild = viewFlipperFlow.indexOfChild(flow.viewFlipperModule)

    }

    flow?.onFinish = {
        viewFlipperFlow.removeView(flow?.viewFlipperModule)
        flow?.viewFlipperModule?.removeAllViews()
        flow = null
    }

    flow?.start()

}

class FlowAuth: BaseFlow() {

    fun start() {

        if (!flows.contains(this)) {
            runModuleAuth()
        }
    }

    fun runModuleAuth() {
        val module = AuthView()
        module.emitEvent = { event ->
            when(AuthViewModel.EventType.valueOf(event)) {
                AuthViewModel.EventType.OnButtonPressed -> runModulePassword()
            }
        }
        push(module)
    }

    fun runModulePassword() {
        val module = PasswordView()
        module.emitEvent = { event ->
            when(PasswordViewModel.EventType.valueOf(event)) {
                PasswordViewModel.EventType.OnBackPressed -> pop()
            }
        }
        push(module)
    }

}