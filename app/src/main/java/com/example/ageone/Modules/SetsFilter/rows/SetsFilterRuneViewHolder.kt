package com.example.ageone.Modules.SetsFilter.rows

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.Application.utils
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.View.BaseView
import yummypets.com.stevia.*

class SetsFilterRuneViewHolder(val constraintLayout: ConstraintLayout) : BaseViewHolder(constraintLayout) {

    val checkColor = Color.WHITE
    val uncheckColor = Color.rgb(0xD5,0xC9,0xF1)

    val back by lazy {
        val view = BaseView()
        view.backgroundColor = uncheckColor
        view.cornerRadius = 8.dp
        view.initialize()
        view
    }

    var isChecked = false

    val imageViewRune by lazy {
        val imageViewRune = BaseImageView()
        imageViewRune
    }

    init {
        /*constraintLayout.setOnClickListener {
            if (isChecked) {
                back.backgroundColor = uncheckColor
            } else {
                back.backgroundColor = checkColor
            }
            back.initialize()
            isChecked = !isChecked
        }*/
        renderUI()
    }
}

fun SetsFilterRuneViewHolder.renderUI() {
    constraintLayout.subviews(
        back,
        imageViewRune
    )

    back
        .constrainTopToTopOf(constraintLayout, 16)
        .constrainRightToRightOf(constraintLayout, 8)
        .width((utils.variable.displayWidth - 90) / 4)
        .height(utils.variable.displayWidth * .29F)

    imageViewRune
        .constrainCenterYToCenterYOf(back)
        .constrainCenterXToCenterXOf(back)
        .width((utils.variable.displayWidth - 90) / 4 - 40)
        .height(utils.variable.displayWidth * .29F - 52)
}

fun SetsFilterRuneViewHolder.initialize(rune: Int) {
    imageViewRune.setBackgroundResource(rune)
}
