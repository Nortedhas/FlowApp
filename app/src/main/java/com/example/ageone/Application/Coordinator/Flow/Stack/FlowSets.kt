package com.example.ageone.Application.Coordinator.Flow.Stack

import androidx.core.view.size
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.example.ageone.Application.Coordinator.Flow.Regular.runFlowPleer
import com.example.ageone.Application.Coordinator.Router.DataFlow
import com.example.ageone.Application.Coordinator.Router.TabBar.Stack.flows
import com.example.ageone.Application.coordinator
import com.example.ageone.External.Base.Flow.BaseFlow
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Modules.Sets.SetsView
import com.example.ageone.Modules.Sets.SetsViewModel
import com.example.ageone.Modules.SetsFilter.SetsFilterView
import com.example.ageone.Modules.SetsFilterList.SetsFilterListView
import com.example.ageone.Modules.SetsFilterListViewModel
import com.example.ageone.Modules.SetsFilterViewModel
import com.example.ageone.Modules.SetsIn.SetsInView
import com.example.ageone.Modules.SetsInViewModel

fun FlowCoordinator.runFlowSets() {

    var flow: FlowSets? = FlowSets()

    flow?.let{ flow ->

        viewFlipperFlow.addView(flow.viewFlipperModule)
        viewFlipperFlow.displayedChild = viewFlipperFlow.indexOfChild(flow.viewFlipperModule)

        flow.settingsCurrentFlow = DataFlow(viewFlipperFlow.size - 1)

        flows.add(flow)
    }

    flow?.onFinish = {
        viewFlipperFlow.removeView(flow?.viewFlipperModule)
        flow?.viewFlipperModule?.removeAllViews()
        flow?.previousFlow = null
        flow = null
    }

//    flow?.start()
}

class FlowSets: BaseFlow() {

    override fun start() {
        onStarted()
        runModuleSets()
    }

    fun runModuleSets() {
        val module = SetsView()

        settingsCurrentFlow.isBottomNavigationVisible = true

        module.emitEvent = { event ->
            when(SetsViewModel.EventType.valueOf(event)) {
                SetsViewModel.EventType.OnTestPressed -> {
                    runModuleSetsFilter()

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
            isBottomNavigationVisible = false,
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

    fun runModuleSetsFilter() {
        val module = SetsFilterView(InitModuleUI(
            isBottomNavigationVisible = false,
            backListener = {
                pop()
            }
        ))
        settingsCurrentFlow.isBottomNavigationVisible = false

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
        settingsCurrentFlow.isBottomNavigationVisible = false

        module.emitEvent = { event ->
            when (SetsFilterListViewModel.EventType.valueOf(event)) {
                SetsFilterListViewModel.EventType.OnSetPressed -> {
                    runModuleSetsIn()
                }
            }
        }
        push(module)
    }

}