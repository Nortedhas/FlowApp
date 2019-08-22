package com.example.ageone.Modules.MeditationFilter.rows

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextView.BaseTextView
import com.example.ageone.External.Base.View.BaseView
import yummypets.com.stevia.*

class MeditationFilterButtonViewHolder(val constraintLayout: ConstraintLayout): BaseViewHolder(constraintLayout) {
    val rectangleUp by lazy {
        val view = BaseView()
        view.backgroundColor = Color.rgb(0x98, 0x9E, 0xDD)
        view.cornerRadius = 30.dp
        view.initialize()
        view
    }

    val rectangleDown by lazy {
        val view = BaseView()
        view.backgroundColor = Color.rgb(0x98, 0x9E, 0xDD)
        view.initialize()
        view
    }

    val textViewSearch by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.CENTER
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textSize = 21F
        textView.textColor = Color.WHITE
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }
    init {

        renderUI()
    }
}

fun MeditationFilterButtonViewHolder.renderUI() {

    constraintLayout.subviews(
        rectangleUp,
        rectangleDown,
        textViewSearch
    )

    rectangleUp
        .fillHorizontally()
        .constrainTopToTopOf(constraintLayout, 80)
        .height(80)

    rectangleDown
        .fillHorizontally()
        .constrainTopToTopOf(rectangleUp, 40)
        .height(40)

    textViewSearch
        .constrainCenterYToCenterYOf(rectangleUp)
        .constrainLeftToLeftOf(rectangleUp)
        .constrainRightToRightOf(rectangleUp)
}

fun MeditationFilterButtonViewHolder.initialize(text: String) {
    textViewSearch.text = text
}