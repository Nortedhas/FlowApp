package com.example.ageone.Application.Coordinator.Flow.Stack

import androidx.core.view.size
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.example.ageone.Application.Coordinator.Router.DataFlow
import com.example.ageone.Application.Coordinator.Router.TabBar.Stack
import com.example.ageone.Application.R
import com.example.ageone.Application.api
import com.example.ageone.Application.coordinator
import com.example.ageone.External.Base.Flow.BaseFlow
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Models.User.user
import com.example.ageone.Modules.CodeInvitation.CodeInvitationModel
import com.example.ageone.Modules.CodeInvitation.CodeInvitationView
import com.example.ageone.Modules.Invite.InviteModel
import com.example.ageone.Modules.Invite.InviteView
import com.example.ageone.Modules.Profile.ProfileView
import com.example.ageone.Modules.ProfileModel
import com.example.ageone.Modules.ProfileViewModel
import com.example.ageone.Modules.ProfileVip.ProfileVipView
import com.example.ageone.Modules.ProfileVipModel
import com.example.ageone.Modules.ProfileVipViewModel

fun FlowCoordinator.runFlowProfile() {

    var flow: FlowProfile? = FlowProfile()

    flow?.let{ flow ->
        viewFlipperFlow.addView(flow.viewFlipperModule)
        viewFlipperFlow.displayedChild = viewFlipperFlow.indexOfChild(flow.viewFlipperModule)

        flow.settingsCurrentFlow = DataFlow(viewFlipperFlow.size - 1)

        Stack.flows.add(flow)
    }

    flow?.onFinish = {
        viewFlipperFlow.removeView(flow?.viewFlipperModule)
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
        var modelInvite = InviteModel()
        var modelCodeInvitation = CodeInvitationModel()
    }

    fun runModuleProfile() {

        val module = ProfileView(InitModuleUI(
            exitListener = {
                user.isAuthorized = false
                api.cashTime = 0
//                viewFlipperFlow.removeAllViews()
                coordinator.start()
            },
            iconListener = {
                runModuleInvite()
            }
        ))


        module.viewModel.initialize(models.modelProfile) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = true

        module.emitEvent = { event ->
            when (ProfileViewModel.EventType.valueOf(event)) {
                ProfileViewModel.EventType.OnGetVipPressed -> {
                    runModuleProfileVip()
                }
                ProfileViewModel.EventType.OnCodePressed -> {
                    runModuleCodeInvitation()
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

    fun runModuleInvite() {
        val module = InviteView(InitModuleUI(
            isBottomNavigationVisible = false,
            exitListener = {
                pop()
            },
            exitIcon = R.drawable.ic_close
        ))
        module.viewModel.initialize(models.modelInvite) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false

        module.emitEvent = { event ->
            when (ProfileVipViewModel.EventType.valueOf(event)) {

            }
        }
        push(module)
    }

    fun runModuleCodeInvitation() {
        val module = CodeInvitationView(InitModuleUI(
            isBottomNavigationVisible = false,
            isBackPressed = true
        ))
        module.viewModel.initialize(models.modelCodeInvitation) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false

        module.emitEvent = { event ->
            when (ProfileVipViewModel.EventType.valueOf(event)) {

            }
        }
        push(module)
    }
}