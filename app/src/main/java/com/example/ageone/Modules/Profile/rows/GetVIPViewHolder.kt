package com.example.ageone.Modules.Profile.rows

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.Application.R
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextView.BaseTextView
import com.example.ageone.External.Base.View.BaseView
import yummypets.com.stevia.*

class GetVIPViewHolder(val constraintLayout: ConstraintLayout): BaseViewHolder(constraintLayout) {

    val back by lazy {
        val view = BaseView()
        view.backgroundColor = Color.WHITE
        view.cornerRadius = 8.dp
        view.initialize()
        view.elevation = 3F.dp
        view
    }

    val image by lazy {
        val base = BaseView()
        base.setBackgroundResource(R.drawable.ic_go)
        base
    }

    val textView by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.rgb(0x70,0x7A,0xBA)
        textView.textSize = 18F
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }


    init {

        renderUI()
    }
}

fun GetVIPViewHolder.renderUI() {

    constraintLayout.subviews(
        back.subviews(
            textView,
            image
        )
    )

    back
        .fillHorizontally(8)
        .constrainTopToTopOf(constraintLayout, 24)
        .constrainBottomToBottomOf(constraintLayout, 8)

    image
        .height(43)
        .width(43)
        .constrainTopToTopOf(back, 20)
        .constrainRightToRightOf(back, 16)

    textView
        .fillHorizontally()
        .constrainTopToTopOf(back, 16)
        .constrainBottomToBottomOf(back, 16)
        .constrainLeftToLeftOf(back, 16)
        .constrainRightToLeftOf(image, 8)
}

fun GetVIPViewHolder.initialize(text: String) {
    textView.text = text
}