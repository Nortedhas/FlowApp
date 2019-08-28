package com.example.ageone.External.Base.Toolbar

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.setPadding
import com.example.ageone.Application.R
import com.example.ageone.Application.currentActivity
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.TextView.BaseTextView
import com.example.ageone.External.InitModuleUI
import yummypets.com.stevia.*

class BaseToolbar(val initModuleUI: InitModuleUI): ConstraintLayout(currentActivity) {
    var title: String? = null
    var titleTextColor: Int = Color.WHITE
    var titleTextSize: Float = 20F

    var viewIconRes: Int? = null
    var viewIconSize: Int = 20

    private val textViewTitle by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.CENTER
        textView.typeface = Typeface.DEFAULT
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }

    private val viewExit by lazy {
        val view = BaseImageView()
        view.setImageResource(R.drawable.ic_close)
        view.visibility = View.GONE
        view
    }

    private val viewIcon by lazy {
        val view = BaseImageView()
        view.visibility = View.GONE
        view
    }

    private val viewArrow by lazy {
        val view = BaseImageView()
        view.setImageResource(R.drawable.ic_arrow_back)
        view.visibility = View.GONE
        view
    }

    fun initialize() {
        textViewTitle.text = title
        textViewTitle.textColor = titleTextColor
        textViewTitle.textSize = titleTextSize

        initModuleUI.backListener?.let { backListener ->
            viewArrow.visibility = View.VISIBLE
            viewArrow.setOnClickListener(backListener)
        }

        initModuleUI.exitListener?.let { exitListener ->
            viewExit.visibility = View.VISIBLE
            viewExit.setOnClickListener(exitListener)
        }

        viewIconRes?.let{ iconRes ->
            viewIcon
                .setImageResource(iconRes)

            viewIcon
                .width(viewIconSize)
                .height(viewIconSize)
                .visibility = View.VISIBLE

            initModuleUI.iconListener?.let { iconListener ->
                viewIcon.setOnClickListener(iconListener)
            }
        }

        viewArrow.setPadding(8)

        renderUI()

    }

    private fun renderUI() {
        subviews(
            viewArrow,
            textViewTitle,
            viewIcon,
            viewExit
        )

        viewArrow
            .fillVertically()
            .width(25)
            .height(25)
            .constrainLeftToLeftOf(this, 8)

        textViewTitle
            .fillHorizontally()
            .fillVertically()

        viewIcon
            .fillVertically()
            .constrainRightToLeftOf(viewExit,16)

        viewExit
            .fillVertically()
            .width(15)
            .height(15)
            .constrainRightToRightOf(this, 16)
    }
}