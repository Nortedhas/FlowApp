package com.example.ageone.Application.Coordinator.Flow

import android.graphics.Color
import com.example.ageone.Application.currentActivity
import com.example.ageone.External.Base.Flow.BaseFlow
import com.example.ageone.Modules.Auth.AuthView
import com.example.ageone.Modules.Auth.AuthViewModel
import com.example.ageone.Modules.Password.PasswordView
import com.example.ageone.Modules.Password.PasswordViewModel

fun FlowCoordinator.runFlowAuth() {

    var flow: FlowAuth? = FlowAuth()

    flow?.let{ module ->
        viewFlipperFlow.addView(module.viewFlipperModule)
        viewFlipperFlow.displayedChild = viewFlipperFlow.indexOfChild(module.viewFlipperModule)
        isBottomNavigationVisible(false)
        setStatusBarColor(Color.TRANSPARENT)
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
        runModuleAuth()
    }

    fun runModuleAuth() {
        val module = AuthView(currentActivity)
        module.emitEvent = { event ->
            when(AuthViewModel.EventType.valueOf(event)) {
                AuthViewModel.EventType.OnButtonPressed -> runModulePassword()
            }
        }
        push(module)
    }

    fun runModulePassword() {
        val module = PasswordView(currentActivity)
        module.emitEvent = { event ->
            when(PasswordViewModel.EventType.valueOf(event)) {
                PasswordViewModel.EventType.OnBackPressed -> pop()
            }
        }
        push(module)
    }

}