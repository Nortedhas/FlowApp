package com.example.ageone.Application.Coordinator.Flow.Stack

import android.graphics.Color
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.example.ageone.Application.Coordinator.Flow.setBottomNavigationVisible
import com.example.ageone.Application.Coordinator.Router.TabBar.Stack.flows
import com.example.ageone.External.Base.Flow.BaseFlow
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Modules.Meditation.MeditationView
import com.example.ageone.Modules.Meditation.MeditationViewModel
import timber.log.Timber

fun FlowCoordinator.runFlowMain() {

    var flow: FlowMain? =
        FlowMain()

    flow?.let{ flow ->
        viewFlipperFlow.addView(flow.viewFlipperModule)
        viewFlipperFlow.displayedChild = viewFlipperFlow.indexOfChild(flow.viewFlipperModule)
        viewFlipperFlow

        flow.isBottomNavigationVisible = true
        setBottomNavigationVisible(true)

        flows.add(flow)
    }

    flow?.onFinish = {
        viewFlipperFlow.removeView(flow?.viewFlipperModule)
        flow?.viewFlipperModule?.removeAllViews()
        flow = null
    }

//    flow?.start()
}

class FlowMain: BaseFlow() {

    override fun start() {
        isStarted = true
        runModuleMeditation()
    }

    private fun runModuleMeditation() {
        val module = MeditationView(InitModuleUI(colorToolbar = Color.TRANSPARENT))
        module.emitEvent = { event ->
            when(MeditationViewModel.EventType.valueOf(event)) {
                MeditationViewModel.EventType.OnEnterPressed -> Timber.i("clicked photo")
            }
        }
        push(module)
    }
}