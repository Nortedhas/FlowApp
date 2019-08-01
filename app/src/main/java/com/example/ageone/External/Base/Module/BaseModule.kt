package com.example.ageone.External.Base.Module

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.WindowInsets
import android.widget.ImageView
import android.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.Application.R
import com.example.ageone.Application.currentActivity
import com.example.ageone.Application.utils
import com.example.ageone.External.Base.ConstraintLayout.BaseConstraintLayout
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.Toolbar.BaseToolbar
import com.example.ageone.Internal.Utilities.Utils
import timber.log.Timber
import yummypets.com.stevia.*

open class BaseModule(context: Context?): ConstraintLayout(context) {

    val backgroundImage by lazy {
        val image = BaseImageView()
        image
    }

    val innerContent by lazy {
        val innerContent = BaseConstraintLayout()
        innerContent.fitsSystemWindows = true
        innerContent.setPadding(0, utils.variable.statusBarHeight, 0, 0)
        innerContent
    }

    val toolBar by lazy {
        val toolBar = BaseToolbar()
        toolBar
            .setBackgroundColor(Color.MAGENTA)
        toolBar
    }

    var onDeInit: (() -> Unit)? = null
    var emitEvent: ((String) -> Unit)? = null

    init {
        id = View.generateViewId()
        renderUI()
        Timber.i("${this.className()} Init ")
    }

    fun getClassName(name: String): String {
        return name.split("{")[0]
    }

    fun saveArea() = utils.variable.statusBarHeight + toolBar.height

    fun renderUI() {

        subviews(
            backgroundImage,
            innerContent.subviews(
                toolBar
            )
        )

        innerContent
            .fillHorizontally()
            .fillVertically()

        toolBar
            .constrainTopToTopOf(innerContent, 0)
            .fillHorizontally()
            .height(56)

        backgroundImage
            .fillHorizontally()
            .fillVertically()

    }

    fun className(): String {
        return utils.tools.getClassName(this.toString())
    }

}
