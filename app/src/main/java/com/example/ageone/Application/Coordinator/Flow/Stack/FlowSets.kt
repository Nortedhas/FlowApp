package com.example.ageone.Application.Coordinator.Flow.Stack

import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.example.ageone.Application.Coordinator.Flow.setBottomNavigationVisible
import com.example.ageone.Application.Coordinator.Router.TabBar.Stack.flows
import com.example.ageone.Application.R
import com.example.ageone.External.Base.Flow.BaseFlow
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Modules.Sets.SetsView
import com.example.ageone.Modules.Sets.SetsViewModel
import com.example.ageone.Modules.SetsIn.SetsInView
import com.example.ageone.Modules.SetsInViewModel
import timber.log.Timber

fun FlowCoordinator.runFlowSets() {

    var flow: FlowSets? =
        FlowSets()

    flow?.let{ flow ->
        flow.isBottomNavigationVisible = true

        viewFlipperFlow.addView(flow.viewFlipperModule)
        viewFlipperFlow.displayedChild = viewFlipperFlow.indexOfChild(flow.viewFlipperModule)

        flows.add(flow)
    }

    flow?.onFinish = {
        viewFlipperFlow.removeView(flow?.viewFlipperModule)
        flow?.viewFlipperModule?.removeAllViews()
        flow = null
    }

//    flow?.start()
}

class FlowSets: BaseFlow() {

    override fun start() {
        isStarted = true
        runModuleSets()
    }

    fun runModuleSets() {
        val module = SetsView()
        module.emitEvent = { event ->
            when(SetsViewModel.EventType.valueOf(event)) {
                SetsViewModel.EventType.OnTestPressed -> {
                    Timber.i("Sets module button")

                }
                SetsViewModel.EventType.OnSetPressed -> {
                    runModuleSetsIn()
                }
            }
        }
        push(module)
    }

    fun runModuleSetsIn() {
        val module = SetsInView(InitModuleUI(
            iconNavigation = R.drawable.ic_arrow_back,
            navigationListener = {
                pop()
            }
        ))

        module.emitEvent = { event ->
            when (SetsInViewModel.EventType.valueOf(event)) {

            }
        }
        push(module)
    }

}