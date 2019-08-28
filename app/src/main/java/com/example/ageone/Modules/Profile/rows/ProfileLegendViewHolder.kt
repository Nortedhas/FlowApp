package com.example.ageone.Modules.Profile.rows

import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.Application.R
import com.example.ageone.Application.utils
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import yummypets.com.stevia.*

class ProfileLegendViewHolder(val constraintLayout: ConstraintLayout): BaseViewHolder(constraintLayout) {

    val imageView by lazy {
        val imageView = BaseImageView()
        imageView.setBackgroundResource(R.drawable.pic_legend)
        imageView
    }

    init {

        renderUI()
    }
}

fun ProfileLegendViewHolder.renderUI() {

    constraintLayout.subviews(
        imageView
    )

    imageView
        .width(utils.variable.displayWidth * .91F)
        .height(utils.variable.displayWidth * .32F)
        .constrainTopToTopOf(constraintLayout, 8)
        .constrainBottomToBottomOf(constraintLayout, 8)
        .constrainLeftToLeftOf(constraintLayout)
        .constrainRightToRightOf(constraintLayout)
}

fun ProfileLegendViewHolder.initialize() {

}