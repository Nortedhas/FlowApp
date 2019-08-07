package com.example.ageone.Application.Coordinator.Flow

import android.graphics.Color
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.example.ageone.External.Base.Flow.BaseFlow
import com.example.ageone.Modules.Accaunt.AccauntView
import com.example.ageone.Modules.Accaunt.AccauntViewModel
import timber.log.Timber

fun FlowCoordinator.runFlowMain() {
    var flow: FlowMain? = FlowMain()

    flow?.let{ flow ->
        flow.colorStatusBar = Color.CYAN
        flow.isBottomNavigationVisible = true
        viewFlipperFlow.addView(flow.viewFlipperModule)
        viewFlipperFlow.displayedChild = viewFlipperFlow.indexOfChild(flow.viewFlipperModule)
        
//        setBottomNavigationVisible(true)
        setStatusBarColor(flow.colorStatusBar)

//        FlowCoordinator.stack.flows.add(flow)
    }

    flow?.onFinish = {
        viewFlipperFlow.removeView(flow?.viewFlipperModule)
        flow?.viewFlipperModule?.removeAllViews()
        flow = null
    }

    flow?.start()
}

class FlowMain: BaseFlow() {

    fun start() {
        if (!FlowCoordinator.stack.flows.contains(this)) {
            runModuleAccaunt()
        }
    }

    fun runModuleAccaunt() {
        val module = AccauntView()
        module.emitEvent = { event ->
            when(AccauntViewModel.EventType.valueOf(event)) {
                AccauntViewModel.EventType.OnPhotoClicked -> Timber.i("clicked photo")
            }
        }
        push(module)
    }
}