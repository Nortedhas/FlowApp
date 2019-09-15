package com.example.ageone.Modules.StartLogin.rows

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextView.BaseTextView
import com.example.ageone.External.Base.TextView.underline
import yummypets.com.stevia.*

class EnterLoginTextViewHolder(val constraintLayout: ConstraintLayout): BaseViewHolder(constraintLayout) {

    val textViewLogin by lazy {
        val textViewLogin = BaseTextView()
        textViewLogin.textColor = Color.parseColor("#707ABA")
        textViewLogin.textSize = 15F
        textViewLogin.gravity = Gravity.CENTER
        textViewLogin.typeface = Typeface.DEFAULT
        textViewLogin.setBackgroundColor(Color.TRANSPARENT)
        textViewLogin.underline()
        textViewLogin
    }

    init {
//        constraintLayout.setBackgroundColor(Color.WHITE)
        renderUI()
    }

}

fun EnterLoginTextViewHolder.renderUI() {
    constraintLayout.subviews(
        textViewLogin
    )

    textViewLogin
        .constrainTopToTopOf(constraintLayout, 24)
        .constrainLeftToLeftOf(constraintLayout)
        .constrainRightToRightOf(constraintLayout)
        .constrainBottomToBottomOf(constraintLayout, 24)
}

fun EnterLoginTextViewHolder.initialize(textLogin: String) {
    textViewLogin.text = textLogin
}