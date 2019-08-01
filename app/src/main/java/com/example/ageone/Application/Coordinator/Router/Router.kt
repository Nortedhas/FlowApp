package com.example.ageone.Application.Coordinator.Router

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.transition.*
import com.example.ageone.Application.AppActivity
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