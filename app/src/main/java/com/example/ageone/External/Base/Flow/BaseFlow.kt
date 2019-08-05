package com.example.ageone.External.Base.Flow

import android.graphics.Color
import android.view.View
import androidx.core.view.contains
import com.example.ageone.Application.currentActivity
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.ViewFlipper.BaseViewFlipper
import timber.log.Timber

open class BaseFlow: View(currentActivity){

    val stack = mutableSetOf<Int>()

    var onStart: (() -> Unit)? = null
    var onFinish: (() -> Unit)? = null

    var colorStatusBar = Color.TRANSPARENT
    var isBottomNavigationVisible = false

    val viewFlipperModule by lazy {
        val viewFlipperModule = BaseViewFlipper()
        viewFlipperModule
    }

    init {
        onStart?.invoke()
    }

    fun push(module: BaseModule?) {
        module?.let { module ->
            includeModule(module)
            viewFlipperModule.displayedChild = stack.indexOf(module.id)
        }
    }

    fun pop() {
        if (stack.size > 1) {
            val currentModule = viewFlipperModule.currentView as BaseModule
            deInitModule(currentModule)
            
        }
        viewFlipperModule.displayedChild
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
                viewFlipperModule.displayedChild = stack.last()    
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

}