package com.example.ageone.Application.Coordinator.Flow.Stack

import androidx.core.view.size
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.example.ageone.Application.Coordinator.Flow.Regular.runFlowPleer
import com.example.ageone.Application.Coordinator.Router.DataFlow
import com.example.ageone.Application.Coordinator.Router.TabBar.Stack.flows
import com.example.ageone.Application.coordinator
import com.example.ageone.Application.currentActivity
import com.example.ageone.Application.webSocket
import com.example.ageone.External.Base.Flow.BaseFlow
import com.example.ageone.External.InitModuleUI
import com.example.ageone.External.Libraries.WebView.openUrl
import com.example.ageone.Modules.*
import com.example.ageone.Modules.Meditation.MeditationModel
import com.example.ageone.Modules.Meditation.MeditationView
import com.example.ageone.Modules.Meditation.MeditationViewModel
import com.example.ageone.Modules.MeditationFilter.MeditationFilterView
import com.example.ageone.Modules.MeditationFilterList.MeditationFilterListView
import timber.log.Timber

fun FlowCoordinator.runFlowMain() {

    var flow: FlowMain? = FlowMain()

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

class FlowMain: BaseFlow() {

    private var models = FlowMainModels()

    override fun start() {
        onStarted()
        runModuleMeditation()
    }

    inner class FlowMainModels {
        var modelWebView = WebViewModel()
        var modelMeditation = MeditationModel()
        var modelMeditationFilter = MeditationFilterModel()
        var modelMeditationFilterList = MeditationFilterListModel()
    }

    private fun runModuleMeditation() {
        val module = MeditationView()
        module.viewModel.initialize(models.modelMeditation) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = true

        module.emitEvent = { event ->
            models.modelMeditation = module.viewModel.model

            when(MeditationViewModel.EventType.valueOf(event)) {
                MeditationViewModel.EventType.OnEnterPressed -> {
                    Timber.i("clicked photo")
                }
                MeditationViewModel.EventType.OnSearchPressed -> {
                    runModuleMeditationFilter()
                }
                MeditationViewModel.EventType.OnMeditationPressed -> {
                    coordinator.runFlowPleer(this)
                }
                MeditationViewModel.EventType.OnPayed -> {
                    runModuleWebView(models.modelMeditation.url)
                }
            }
        }
        push(module)
    }

    fun runModuleMeditationFilter() {
        val module = MeditationFilterView(InitModuleUI(
            isBottomNavigationVisible = false,
            isBackPressed = true,
            backListener = {
                pop()
            }
        ))
        module.viewModel.initialize(models.modelMeditationFilter) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false

        module.emitEvent = { event ->
            when (MeditationFilterViewModel.EventType.valueOf(event)) {
                MeditationFilterViewModel.EventType.OnSearchPressed -> {
                    runModuleMeditationFilterList()
                }
            }
        }
        push(module)
    }


    fun runModuleWebView(url: String) {
        val module = WebView(InitModuleUI(
            isBottomNavigationVisible = false,
            isBackPressed = true,
            backListener = {
                pop()
            }
        ), url)

        settingsCurrentFlow.isBottomNavigationVisible = false

        webSocket.socket.on("orderCheck") {message ->
            Timber.i("Pay succes!")
            currentActivity?.runOnUiThread {
                pop()
            }
        }

        push(module)
    }

    fun runModuleMeditationFilterList() {
        val module = MeditationFilterListView(InitModuleUI(
            isBottomNavigationVisible = false,
            isBackPressed = true,
            backListener = {
                pop()
            }
        ))
        module.viewModel.initialize(models.modelMeditationFilterList) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false

        module.emitEvent = { event ->
            when (MeditationFilterListViewModel.EventType.valueOf(event)) {
                MeditationFilterListViewModel.EventType.OnMeditationPressed -> {
                    coordinator.runFlowPleer(this)
                }
            }
        }
        push(module)
    }
}