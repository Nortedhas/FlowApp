package com.example.ageone.Application.Coordinator.Flow.Stack

import androidx.core.view.size
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.currentFlow
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.example.ageone.Application.Coordinator.Flow.Regular.runFlowPleer
import com.example.ageone.Application.Coordinator.Flow.setBottomNavigationVisible
import com.example.ageone.Application.Coordinator.Router.TabBar.Stack.flows
import com.example.ageone.Application.coordinator
import com.example.ageone.External.Base.Flow.BaseFlow
import com.example.ageone.External.Extensions.FlowCoordinator.DataFlow
import com.example.ageone.External.Extensions.FlowCoordinator.pop
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Modules.Meditation.MeditationView
import com.example.ageone.Modules.Meditation.MeditationViewModel
import com.example.ageone.Modules.MeditationFilter.MeditationFilterView
import com.example.ageone.Modules.MeditationFilterList.MeditationFilterListView
import com.example.ageone.Modules.MeditationFilterListViewModel
import com.example.ageone.Modules.MeditationFilterViewModel
import timber.log.Timber

fun FlowCoordinator.runFlowMain(settingsLastFlow: DataFlow) {

    var flow: FlowMain? = FlowMain(settingsLastFlow)

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

class FlowMain(val settingsLastFlow: DataFlow): BaseFlow() {

    override fun start() {
        onStarted()
        currentFlow = this
        runModuleMeditation()
    }

    private fun runModuleMeditation() {
        val module = MeditationView()

        onBack = {
        }
        settingsCurrentFlow.isBottomBarVisible = true

        module.emitEvent = { event ->
            when(MeditationViewModel.EventType.valueOf(event)) {
                MeditationViewModel.EventType.OnEnterPressed -> {
                    Timber.i("clicked photo")
                }
                MeditationViewModel.EventType.OnSearchPressed -> {
                    runModuleMeditationFilter()
                }
                MeditationViewModel.EventType.OnMeditationPressed -> {
                    coordinator.runFlowPleer(settingsCurrentFlow)
                }
            }
        }
        push(module)
    }

    fun runModuleMeditationFilter() {
        val module = MeditationFilterView(InitModuleUI(
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
            when (MeditationFilterViewModel.EventType.valueOf(event)) {
                MeditationFilterViewModel.EventType.OnSearchPressed -> {
                    runModuleMeditationFilterList()
                }
            }
        }
        push(module)
    }

    fun runModuleMeditationFilterList() {
        val module = MeditationFilterListView(InitModuleUI(
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
            when (MeditationFilterListViewModel.EventType.valueOf(event)) {

            }
        }
        push(module)
    }
}