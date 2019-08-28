package com.example.ageone.External.Base.Flow

import android.graphics.Color
import android.view.View
import androidx.core.view.contains
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator
import com.example.ageone.Application.Coordinator.Flow.setBottomNavigationVisible
import com.example.ageone.Application.currentActivity
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.ViewFlipper.BaseViewFlipper
import com.example.ageone.External.Extensions.FlowCoordinator.DataFlow
import timber.log.Timber

abstract class BaseFlow: View(currentActivity){

    val stack = mutableListOf<Int>()

    lateinit var settingsCurrentFlow: DataFlow

    var onStart: (() -> Unit)? = null
    var onFinish: (() -> Unit)? = null

    var onBack: (() -> Unit) = {
        pop()
    }

    var colorStatusBar = Color.TRANSPARENT
//    var isBottomNavigationVisible = false

    var isStarted = false

    val viewFlipperModule by lazy {
        val viewFlipperModule = BaseViewFlipper()
        viewFlipperModule
    }

    init {

        onStart?.invoke()

    }

    fun onStarted(){
        FlowCoordinator.ViewFlipperFlowObject.currentFlow = this
        isStarted = true
    }

    fun push(module: BaseModule?) {
        module?.let { module ->
            includeModule(module)
            viewFlipperModule.displayedChild = stack.indexOf(module.id)
            setBottomNavigationVisible(module.isBottomNavigationVisible)
        }
    }

    fun pop() {
        if (stack.size > 1) {
            val currentModule = viewFlipperModule.currentView as BaseModule
            deInitModule(currentModule)

            setBottomNavigationVisible((viewFlipperModule.currentView as BaseModule).isBottomNavigationVisible)
        }
    }

    fun popToRoot() {

    }

    fun deInitModule(module: BaseModule?) {
        module?.let{ module ->
            
            if (stack.contains(module.id)) {
                stack.remove(module.id)
            }
            
            if (viewFlipperModule.contains(module)) {
                viewFlipperModule.removeView(module)
                viewFlipperModule.displayedChild = stack.size - 1//.last()

            }
            module.onDeInit?.invoke()
            Timber.i("Module DeInit ${module.className()}")
        }
    }

    fun includeModule(module: BaseModule?) {
        module?.let { module ->
            if (!stack.contains(module.id)){
                stack.add(module.id)
                viewFlipperModule.addView(module)
            }
        }
    }

    abstract fun start()
}