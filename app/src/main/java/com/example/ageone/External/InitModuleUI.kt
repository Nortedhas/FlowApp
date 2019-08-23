package com.example.ageone.External

import android.graphics.Color
import android.view.View
import com.example.ageone.Application.Coordinator.Flow.isBottomNavigationExist
import com.google.android.material.bottomnavigation.BottomNavigationView

data class InitModuleUI (
    var isBottomNavigationVisible: Boolean = isBottomNavigationExist,
    var isHidden: Boolean = false,
    var colorToolbar: Int = Color.TRANSPARENT,
    var iconNavigation: Int? = null,
    var navigationListener: ((View)->(Unit))? = null
)