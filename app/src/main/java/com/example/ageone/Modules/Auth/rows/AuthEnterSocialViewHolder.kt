package com.example.ageone.Modules.Auth.rows

import android.graphics.Color
import android.graphics.Typeface
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.Application.R
import com.example.ageone.External.Base.Button.BaseButton
import com.example.ageone.External.Base.ViewHolder.BaseViewHolder
import yummypets.com.stevia.*

class AuthEnterSocialViewHolder(itemView: View): BaseViewHolder(itemView) {
    val enterButton by lazy {
        val button = BaseButton()

        button.textSize = 15F
        button.typeface = Typeface.DEFAULT

        button.backgroundColor = Color.WHITE
        button.cornerRadius = 60
        button.imageIcon = R.drawable.phone//size???

        button
    }

    init {
        if (itemView is ConstraintLayout) {
            constraintLayout = itemView
            renderUI()
        }
    }
}

fun AuthEnterSocialViewHolder.renderUI() {
    constraintLayout?.let { constraintLayout ->
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
fun AuthEnterSocialViewHolder.initialize(textEnter: String, imageRes: Int, color: Int) {
    enterButton.text = textEnter
    enterButton.textColor = color
    enterButton.imageIcon = imageRes
    enterButton.borderWidth = 1
    enterButton.borderColor = color

    enterButton.initialize()
}