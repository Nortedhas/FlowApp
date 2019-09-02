package com.example.ageone.Application.Coordinator.Router

import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.currentFlow
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.example.ageone.Application.Coordinator.Flow.setBottomNavigationVisible
import com.example.ageone.Application.currentActivity
import com.example.ageone.Application.hideKeyboard
import com.example.ageone.Application.router
import com.example.ageone.External.Extensions.Function.guard
import timber.log.Timber
import yummypets.com.stevia.style

class Router {
    lateinit var layout: ConstraintLayout

    fun onBackPressed() {

        val current = currentFlow.guard {
            Timber.e("Current flow is null")
            return
        }

        if (current!!.stack.size > 1) {

            // MARK: in flow - pop module from flow
            Timber.i("pop module")

            current.pop()

        } else {

            // MARK: pop flow - change current flow to previous if it exists
            Timber.i("pop flow")

            val previousFlow = current.previousFlow.guard {
                Timber.e("Previous flow is null")
                return
            }

            currentFlow = previousFlow
            viewFlipperFlow.displayedChild = previousFlow!!.settingsCurrentFlow.indexOnFlipperFlow
            setBottomNavigationVisible(previousFlow!!.settingsCurrentFlow.isBottomNavigationVisible)
            currentActivity?.hideKeyboard()

        }

    }


    fun initialize() {
        // MARK: app's root layout
        layout = ConstraintLayout(currentActivity)

        router.layout.style {
            isFocusable = true
            isFocusableInTouchMode = true

        }

    }
}

// MARK: settings for correcting routing (pop function)

data class DataFlow(
    val indexOnFlipperFlow: Int = 0,
    var isBottomNavigationVisible: Boolean = false
)