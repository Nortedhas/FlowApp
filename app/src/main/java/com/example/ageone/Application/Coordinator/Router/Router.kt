package com.example.ageone.Application.Coordinator.Router

import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.currentFlow
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.example.ageone.Application.Coordinator.Flow.setBottomNavigationVisible
import com.example.ageone.Application.currentActivity
import com.example.ageone.Application.router
import com.example.ageone.External.Base.Flow.BaseFlow
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Extensions.Function.guard
import timber.log.Timber
import yummypets.com.stevia.style

class Router {
    val collection = arrayListOf<BaseFlow>()
    lateinit var layout: ConstraintLayout

    init {

    }


    fun onBackPressed() {

        val current = currentFlow.guard {
            Timber.e("Current flow is null")
            return
        }

        if (current!!.stack.size > 1) {

            // MARK: in flow
            Timber.i("pop module")

            current.pop()

        } else {

            // MARK: back last flow

            Timber.i("pop flow")
            val previousFlow = current.previousFlow.guard {
                Timber.e("Previous flow is null")
                return
            }

            currentFlow = previousFlow
            viewFlipperFlow.displayedChild = previousFlow!!.settingsCurrentFlow.indexOnFlipperFlow
            setBottomNavigationVisible(previousFlow!!.settingsCurrentFlow.isBottomBarVisible)


        }


    }



    fun initialize() {

        layout = ConstraintLayout(currentActivity)

        router.layout.style {
            isFocusable = true
            isFocusableInTouchMode = true

        }

    }

    fun addModule(module: BaseModule) {

        Timber.i("Add module to Router")

    }

    fun pop() {

        Timber.i("Delete module")

    }

    fun setCurrentFLow(baseFlow: BaseFlow) {

    }
}