package com.example.ageone.Modules.Purchases.rows

import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.Application.R
import com.example.ageone.Application.utils
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import yummypets.com.stevia.*

class PurchasesEmptyViewHolder(val constraintLayout: ConstraintLayout) : BaseViewHolder(constraintLayout) {

    val imageView by lazy {
        val imageView = BaseImageView()
        imageView.setBackgroundResource(R.drawable.empty_buys)
        imageView
    }

    init {

        renderUI()
    }
}

fun PurchasesEmptyViewHolder.renderUI() {
    constraintLayout.subviews(
        imageView
    )

    imageView
        .height(utils.variable.displayWidth * .37F)
        .width(utils.variable.displayWidth * .67F)
        .constrainTopToTopOf(constraintLayout, (utils.variable.displayWidth * .54F).toInt())
        .constrainLeftToLeftOf(constraintLayout)
        .constrainRightToRightOf(constraintLayout)

}

fun PurchasesEmptyViewHolder.initialize() {

}
