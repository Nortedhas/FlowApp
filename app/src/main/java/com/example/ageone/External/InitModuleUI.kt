package com.example.ageone.External

import android.graphics.Color
import android.view.View

data class InitModuleUI (
    var isHidden: Boolean = false,
    var colorToolbar: Int = Color.TRANSPARENT,
    var iconNavigation: Int? = null,
    var navigationListener: ((View)->(Unit))? = null
)