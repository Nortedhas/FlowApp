package com.example.ageone.Application.Coordinator.Flow

import android.graphics.Color
import com.example.ageone.External.Base.Flow.BaseFlow
import com.example.ageone.Modules.Accaunt.AccauntView
import com.example.ageone.Modules.Accaunt.AccauntViewModel
import timber.log.Timber

fun FlowCoordinator.runFlowMain() {
    var flow: FlowMain? = FlowMain()

    flow?.let{ module ->
        module.colorStatusBar = Color.CYAN
        viewFlipperFlow.addView(module.viewFlipperModule)
        viewFlipperFlow.displayedChild = viewFlipperFlow.indexOfChild(module.viewFlipperModule)
        
        isBottomNavigationVisible(true)
        setStatusBarColor(module.colorStatusBar)
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
        runModuleAccaunt()
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