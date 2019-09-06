package com.example.ageone.Application.Coordinator.Flow.Stack

import androidx.core.view.size
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.example.ageone.Application.Coordinator.Router.DataFlow
import com.example.ageone.Application.Coordinator.Router.TabBar.Stack
import com.example.ageone.Application.coordinator
import com.example.ageone.External.Base.Flow.BaseFlow
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Models.User.user
import com.example.ageone.Modules.*
import com.example.ageone.Modules.Profile.ProfileView
import com.example.ageone.Modules.ProfileVip.ProfileVipView

fun FlowCoordinator.runFlowProfile() {

    var flow: FlowProfile? = FlowProfile()

    flow?.let{ flow ->
        viewFlipperFlow.addView(flow.viewFlipperModule)
        viewFlipperFlow.displayedChild = viewFlipperFlow.indexOfChild(flow.viewFlipperModule)

        flow.settingsCurrentFlow = DataFlow(viewFlipperFlow.size - 1)

        Stack.flows.add(flow)
    }

    flow?.onFinish = {
        FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow.removeView(flow?.viewFlipperModule)
        flow?.viewFlipperModule?.removeAllViews()
        flow = null
    }

//    flow?.start()
}

class FlowProfile: BaseFlow() {

    private var models = FlowProfileModels()

    override fun start() {
        onStarted()
        runModuleProfile()
    }

    inner class FlowProfileModels {
        var modelProfile = ProfileModel()
        var modelProfileVip = ProfileVipModel()
    }

    fun runModuleProfile() {
        val module = ProfileView(
            InitModuleUI(
            exitListener = {
                user.isAuthorized = false
//                viewFlipperFlow.removeAllViews()
                coordinator.start()
            }
        ))
        module.viewModel.initialize(models.modelProfile) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = true

        module.emitEvent = { event ->
            when (ProfileViewModel.EventType.valueOf(event)) {
                ProfileViewModel.EventType.OnGetVipPressed -> {
                    runModuleProfileVip()
                }
            }
        }
        push(module)
    }

    fun runModuleProfileVip() {
        val module = ProfileVipView(InitModuleUI(
            isBottomNavigationVisible = false,
            isBackPressed = true,
            backListener = {
                pop()
            }
        ))
        module.viewModel.initialize(models.modelProfileVip) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false

        module.emitEvent = { event ->
            when (ProfileVipViewModel.EventType.valueOf(event)) {

            }
        }
        push(module)
    }
}