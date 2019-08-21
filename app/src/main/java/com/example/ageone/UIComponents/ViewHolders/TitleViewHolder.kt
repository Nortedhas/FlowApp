package com.example.ageone.UIComponents.ViewHolders

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*

class TitleViewHolder(val constraintLayout: ConstraintLayout): BaseViewHolder(constraintLayout) {
    val textViewTitle by lazy {
        val textView = BaseTextView()

        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textSize = 21F
        textView.setBackgroundColor(Color.TRANSPARENT)

        textView
    }


    init {
        renderUI()
    }

}

fun TitleViewHolder.renderUI() {
    constraintLayout.subviews(
        textViewTitle
    )

    textViewTitle
        .constrainTopToTopOf(constraintLayout, 24)
        .constrainLeftToLeftOf(constraintLayout, 16)
}

fun TitleViewHolder.initialize(textPopular: String, textColor: Int) {
    textViewTitle.text = textPopular
    textViewTitle.textColor = textColor
}

