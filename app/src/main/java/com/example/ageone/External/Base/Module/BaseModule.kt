package com.example.ageone.External.Base.Module

import android.graphics.Color
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ageone.Application.currentActivity
import com.example.ageone.Application.utils
import com.example.ageone.External.Base.ConstraintLayout.BaseConstraintLayout
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.RecyclerView.BaseRecyclerView
import com.example.ageone.External.Base.Toolbar.BaseToolbar
import timber.log.Timber
import yummypets.com.stevia.*

open class BaseModule : ConstraintLayout(currentActivity) {

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

    val viewManagerBodyTable by lazy {
        val viewManager = LinearLayoutManager(currentActivity)
        viewManager
    }
    
    val bodyTable by lazy {
        val recyclerView = BaseRecyclerView()
        recyclerView.layoutManager = viewManagerBodyTable
        recyclerView
    }

    var onDeInit: (() -> Unit)? = null
    var emitEvent: ((String) -> Unit)? = null

    init {
        id = View.generateViewId()
        renderUI()
        Timber.i("${this.className()} Init ")
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
