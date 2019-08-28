package com.example.ageone.Application.Coordinator.Flow.Stack

import androidx.core.view.size
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.example.ageone.Application.Coordinator.Flow.Regular.runFlowSet
import com.example.ageone.Application.Coordinator.Router.TabBar.Stack.flows
import com.example.ageone.Application.coordinator
import com.example.ageone.External.Base.Flow.BaseFlow
import com.example.ageone.External.Extensions.FlowCoordinator.DataFlow
import com.example.ageone.External.Extensions.FlowCoordinator.pop
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Modules.Sets.SetsView
import com.example.ageone.Modules.Sets.SetsViewModel
import com.example.ageone.Modules.SetsFilter.SetsFilterView
import com.example.ageone.Modules.SetsFilterList.SetsFilterListView
import com.example.ageone.Modules.SetsFilterListViewModel
import com.example.ageone.Modules.SetsFilterViewModel

fun FlowCoordinator.runFlowSets(settingsLastFlow: DataFlow) {

    var flow: FlowSets? = FlowSets(settingsLastFlow)

    flow?.let{ flow ->

        viewFlipperFlow.addView(flow.viewFlipperModule)
        viewFlipperFlow.displayedChild = viewFlipperFlow.indexOfChild(flow.viewFlipperModule)

        flow.settingsCurrentFlow = DataFlow(viewFlipperFlow.size - 1)

        flows.add(flow)
    }

    flow?.onFinish = {
        viewFlipperFlow.removeView(flow?.viewFlipperModule)
        flow?.viewFlipperModule?.removeAllViews()
        flow = null
    }

//    flow?.start()
}

class FlowSets(val settingsLastFlow: DataFlow): BaseFlow() {

    override fun start() {
        onStarted()
        FlowCoordinator.ViewFlipperFlowObject.currentFlow = this
        runModuleSets()
    }

    fun runModuleSets() {
        val module = SetsView()

        onBack = {

        }
        settingsCurrentFlow.isBottomBarVisible = true

        module.emitEvent = { event ->
            when(SetsViewModel.EventType.valueOf(event)) {
                SetsViewModel.EventType.OnTestPressed -> {
                    runModuleSetsFilter()

                }
                SetsViewModel.EventType.OnSetPressed -> {
                    coordinator.runFlowSet(settingsCurrentFlow)
                }
            }
        }
        push(module)
    }

    fun runModuleSetsFilter() {
        val module = SetsFilterView(InitModuleUI(
            isBottomNavigationVisible = false,
            backListener = {
                pop()
            }
        ))

        onBack = {
            pop()
        }
        settingsCurrentFlow.isBottomBarVisible = false

        module.emitEvent = { event ->
            when (SetsFilterViewModel.EventType.valueOf(event)) {
                SetsFilterViewModel.EventType.OnSearchPressed -> {
                    runModuleSetsFilterList()
                }
            }
        }
        push(module)
    }

    fun runModuleSetsFilterList() {
        val module = SetsFilterListView(InitModuleUI(
            isBottomNavigationVisible = false,
            backListener = {
                pop()
            }
        ))
        onBack = {
            pop()
        }
        settingsCurrentFlow.isBottomBarVisible = false

        module.emitEvent = { event ->
            when (SetsFilterListViewModel.EventType.valueOf(event)) {

            }
        }
        push(module)
    }

}