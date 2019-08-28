package com.example.ageone.Application.Coordinator.Flow.Regular

import androidx.core.view.size
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.example.ageone.Application.coordinator
import com.example.ageone.External.Base.Flow.BaseFlow
import com.example.ageone.External.Extensions.FlowCoordinator.DataFlow
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Modules.Entry.EntryView
import com.example.ageone.Modules.EntrySMS.EntrySMSView
import com.example.ageone.Modules.EntrySMSViewModel
import com.example.ageone.Modules.EntryViewModel
import com.example.ageone.Modules.Registration.RegistrationView
import com.example.ageone.Modules.Registration.RegistrationViewModel
import com.example.ageone.Modules.RegistrationSMS.RegistrationSMSView
import com.example.ageone.Modules.RegistrationSMSViewModel
import com.example.ageone.Modules.Start.StartView
import com.example.ageone.Modules.Start.StartViewModel
import com.example.ageone.Modules.StartLogin.StartLoginView
import com.example.ageone.Modules.StartLogin.StartLoginViewModel

fun FlowCoordinator.runFlowAuth(settingsLastFlow: DataFlow) {

    var flow: FlowAuth? = FlowAuth(settingsLastFlow)

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

class FlowAuth(val settingsLastFlow: DataFlow): BaseFlow() {

    override fun start() {
        onStarted()
        FlowCoordinator.ViewFlipperFlowObject.currentFlow = this
        runModuleStart()
    }

    fun runModuleStart() {
        val module = StartView(InitModuleUI(
            isBottomNavigationVisible = false,
            isToolbarHidden = true
        ))

        onBack = {
        }
        settingsCurrentFlow.isBottomBarVisible = false

        module.emitEvent = { event ->
            when(StartViewModel.EventType.valueOf(event)) {
                StartViewModel.EventType.OnEnterPressed -> {
                    runModuleStartLogin()
                }

            }
        }
        push(module)
    }

    fun runModuleStartLogin() {
        val module = StartLoginView(InitModuleUI(
            isBottomNavigationVisible = false,
            isToolbarHidden = true
        ))

        onBack = {
            pop()
        }
        settingsCurrentFlow.isBottomBarVisible = false

        module.emitEvent = { event ->
            when(StartLoginViewModel.EventType.valueOf(event)) {
                StartLoginViewModel.EventType.OnVkPressed -> {
                    coordinator.start()
                    onFinish?.invoke()
                }
                StartLoginViewModel.EventType.OnRegistrationPhonePressed -> {
                    runModuleRegistration()
                }
                StartLoginViewModel.EventType.OnEntryPressed -> {
                    runModuleEntry()
                }

            }
        }
        push(module)
    }

    fun runModuleRegistration() {
        val module = RegistrationView(InitModuleUI(
            isBottomNavigationVisible = false
        ))

        onBack = {
            pop()
        }
        settingsCurrentFlow.isBottomBarVisible = false

        module.emitEvent = { event ->
            when(RegistrationViewModel.EventType.valueOf(event)) {
                RegistrationViewModel.EventType.OnRegistrationPressed -> {
                    runModuleRegistrationSMS()
                }
            }
        }
        push(module)
    }

    fun runModuleRegistrationSMS() {
        val module = RegistrationSMSView(InitModuleUI(
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
            when (RegistrationSMSViewModel.EventType.valueOf(event)) {
                RegistrationSMSViewModel.EventType.OnAcceptPressed -> {
                    coordinator.start()
                }
            }
        }
        push(module)
    }

    fun runModuleEntry() {
        val module = EntryView(InitModuleUI(
            isBottomNavigationVisible = false
        ))

        onBack = {
            pop()
        }
        settingsCurrentFlow.isBottomBarVisible = false

        module.emitEvent = { event ->
            when (EntryViewModel.EventType.valueOf(event)) {
                EntryViewModel.EventType.OnEnterPressed -> {
                    runModuleEntrySMS()
                }
            }
        }
        push(module)
    }

    fun runModuleEntrySMS() {
        val module = EntrySMSView(InitModuleUI(
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
            when (EntrySMSViewModel.EventType.valueOf(event)) {


            }
        }
        push(module)
    }
}