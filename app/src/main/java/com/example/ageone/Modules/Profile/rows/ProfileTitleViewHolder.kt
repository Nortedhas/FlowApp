package com.example.ageone.Modules.Profile.rows

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*

class ProfileTitleViewHolder(val constraintLayout: ConstraintLayout): BaseViewHolder(constraintLayout) {

    val textViewTitle by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.textColor = Color.rgb(0x70,0x7A,0xBA)
        textView.textSize = 20F
        textView
    }

    val textViewSub by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.textColor = Color.parseColor("#979797")
        textView.textSize = 11F
        textView
    }

    init {

        renderUI()
    }
}

fun ProfileTitleViewHolder.renderUI() {

    constraintLayout.subviews(
        textViewTitle,
        textViewSub
    )

    textViewTitle
        .constrainTopToTopOf(constraintLayout, 24)
        .fillHorizontally(8)

    textViewSub
        .constrainTopToBottomOf(textViewTitle, 4)
        .fillHorizontally(8)
}

fun ProfileTitleViewHolder.initialize(text: String, sub: String) {
    textViewTitle.text = text
    textViewSub.text = sub
}