package com.example.ageone.Application.Coordinator.Flow

import android.graphics.Color
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.example.ageone.Application.Coordinator.Router.TabBar.Stack.flows
import com.example.ageone.External.Base.Flow.BaseFlow
import com.example.ageone.Modules.Auth.OneView
import com.example.ageone.Modules.Auth.OneViewModel
import timber.log.Timber

fun FlowCoordinator.runFlowOne() {

    var flow: FlowOne? = FlowOne()

    flow?.let{ flow ->
        flow.colorStatusBar = Color.YELLOW
        flow.isBottomNavigationVisible = true

        viewFlipperFlow.addView(flow.viewFlipperModule)
        viewFlipperFlow.displayedChild = viewFlipperFlow.indexOfChild(flow.viewFlipperModule)

        setStatusBarColor(Color.TRANSPARENT)

        flows.add(flow)
    }

    flow?.onFinish = {
        viewFlipperFlow.removeView(flow?.viewFlipperModule)
        flow?.viewFlipperModule?.removeAllViews()
        flow = null
    }

    flow?.start()
}

class FlowOne: BaseFlow() {

    fun start() {
        if (!flows.contains(this)) {
            runModuleOne()
        }
        runModuleOne()
    }

    fun runModuleOne() {
        val module = OneView()
        module.emitEvent = { event ->
            when(OneViewModel.EventType.valueOf(event)) {
                OneViewModel.EventType.OnButtonPressed -> Timber.i("One module button")
            }
        }
        push(module)
    }

}