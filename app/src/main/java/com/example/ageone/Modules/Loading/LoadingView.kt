package com.example.ageone.Modules

import com.example.ageone.Application.R
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.InitModuleUI
import yummypets.com.stevia.subviews

class LoadingView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = LoadingViewModel()

    init {
        setBackgroundResource(R.drawable.base_background)

        innerContent.subviews(
        )

        viewModel.startLoading {
            emitEvent?.invoke(LoadingViewModel.EventType.onFinish.toString())
        }
    }

}
