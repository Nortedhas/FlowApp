package com.example.ageone.Modules.Meditation.rows

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*

class MeditationTitleViewHolder(constraintLayout: ConstraintLayout): BaseViewHolder(constraintLayout) {
    val textViewTitle by lazy {
        val textView = BaseTextView()

        textView.gravity = Gravity.CENTER
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textSize = 21F
        textView.textColor = Color.parseColor("#707ABA")
        textView.setBackgroundColor(Color.TRANSPARENT)

        textView
    }


    init {

        constraintLayout.subviews(
            textViewTitle
        )

        textViewTitle
            .constrainTopToTopOf(constraintLayout, 24)
            .constrainLeftToLeftOf(constraintLayout, 16)
    }
}

fun MeditationTitleViewHolder.initialize(textPopular: String) {
    textViewTitle.text = textPopular
}

