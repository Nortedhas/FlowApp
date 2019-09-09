package com.example.ageone.Application.Coordinator.Flow

import androidx.core.view.size
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.example.ageone.Application.Coordinator.Router.DataFlow
import com.example.ageone.Application.coordinator
import com.example.ageone.External.Base.Flow.BaseFlow
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Modules.Start.StartModel
import com.example.ageone.Modules.Start.StartView
import com.example.ageone.Modules.Start.StartViewModel

fun FlowCoordinator.runFlowLoading() {

    var flow: FlowLoading? =
        FlowLoading()

    flow?.let{ flow ->

        viewFlipperFlow.addView(flow.viewFlipperModule)
        viewFlipperFlow.displayedChild = viewFlipperFlow.indexOfChild(flow.viewFlipperModule)

        flow.settingsCurrentFlow = DataFlow(viewFlipperFlow.size - 1)

    }

    flow?.onFinish = {
        viewFlipperFlow.removeView(flow?.viewFlipperModule)
        flow?.viewFlipperModule?.removeAllViews()
        flow = null
    }

    flow?.start()

}

class FlowLoading: BaseFlow() {

    private var models = FlowAuthModels()

    override fun start() {
        onStarted()
        runModuleStart()
    }

    inner class FlowAuthModels {
        var modelStart = StartModel()
    }

    fun runModuleStart() {
        val module = StartView(InitModuleUI(
            isBottomNavigationVisible = false,
            isToolbarHidden = true
        ))
        module.viewModel.initialize(models.modelStart) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false

        module.emitEvent = { event ->
            when(StartViewModel.EventType.valueOf(event)) {
                StartViewModel.EventType.OnEnterPressed -> {

                }

            }
        }
        push(module)
    }


    fun BaseModule.startMainFlow() {
        coordinator.start()
        onFinish?.invoke()
    }
}