package com.example.ageone.Application.Coordinator.Router

import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.Application.currentActivity
import com.example.ageone.External.Base.Module.BaseModule
import timber.log.Timber

class Router {
    val collection = arrayListOf<BaseModule>()
    lateinit var layout: ConstraintLayout

    init {

    }

    fun initialize() {

        layout = ConstraintLayout(currentActivity)

    }

    fun addModule(module: BaseModule) {

        Timber.i("Add module to Router")

    }

    fun pop() {

        Timber.i("Delete module")

    }
}