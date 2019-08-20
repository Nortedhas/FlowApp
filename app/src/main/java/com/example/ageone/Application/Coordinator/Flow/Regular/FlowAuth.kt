package com.example.ageone.Application.Coordinator.Flow.Regular

import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.example.ageone.Application.coordinator
import com.example.ageone.External.Base.Flow.BaseFlow
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Modules.Start.StartView
import com.example.ageone.Modules.Start.StartViewModel
import com.example.ageone.Modules.StartLogin.StartLoginView
import com.example.ageone.Modules.StartLogin.StartLoginViewModel

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

    override fun start() {
        isStarted = true
        runModuleStart()
    }

    fun runModuleStart() {
        val module = StartView(/*InitModuleUI(isHidden = true)*/)

        module.emitEvent = { event ->
            when(StartViewModel.EventType.valueOf(event)) {
                StartViewModel.EventType.OnEnterPressed -> {
                    runModuleAuth()
                }

            }
        }
        push(module)
    }

    fun runModuleAuth() {
        val module = StartLoginView(InitModuleUI(isHidden = true))

        module.emitEvent = { event ->
            when(StartLoginViewModel.EventType.valueOf(event)) {
                StartLoginViewModel.EventType.OnVkPressed -> {
                    coordinator.start()
                    onFinish?.invoke()
                }

            }
        }
        push(module)
    }

}