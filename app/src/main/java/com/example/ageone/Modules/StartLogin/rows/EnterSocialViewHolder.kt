package com.example.ageone.Modules.StartLogin.rows

import android.graphics.Color
import android.graphics.Typeface
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.Application.R
import com.example.ageone.External.Base.Button.BaseButton
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import yummypets.com.stevia.*

class EnterSocialViewHolder(constraintLayout: ConstraintLayout): BaseViewHolder(constraintLayout) {
    val enterButton by lazy {
        val button = BaseButton()

        button.textSize = 15F
        button.typeface = Typeface.DEFAULT

        button.backgroundColor = Color.WHITE
        button.cornerRadius = 60
        button.imageIcon = R.drawable.phone//sizeIcon???

        button
    }

    init {

        constraintLayout.subviews(
            enterButton
        )

        constraintLayout.setBackgroundColor(Color.WHITE)
        enterButton
            .constrainTopToTopOf(constraintLayout, 24)
            .constrainLeftToLeftOf(constraintLayout)
            .constrainRightToRightOf(constraintLayout)
            .fillHorizontally(56)
    }
}

fun EnterSocialViewHolder.initialize(textEnter: String, imageRes: Int, color: Int, size: Pair<Float, Float>) {

    enterButton.text = textEnter
    enterButton.textColor = color
    enterButton.imageIcon = imageRes
    enterButton.borderWidth = 1
    enterButton.borderColor = color
    enterButton.sizeIcon = size

    enterButton.initialize()
}

