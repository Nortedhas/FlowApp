package com.example.ageone.Modules.Purchases.rows

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.Application.R
import com.example.ageone.Application.utils
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*

class PurchasesEmptyViewHolder(val constraintLayout: ConstraintLayout) : BaseViewHolder(constraintLayout) {

    val imageView by lazy {
        val imageView = BaseImageView()
        imageView.setBackgroundResource(R.drawable.pic_empty_buys)
        imageView
    }

    val textViewTitle by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.textColor = Color.parseColor("#707ABA")
        textView.textSize = 19F
        textView
    }

    init {

        renderUI()
    }
}

fun PurchasesEmptyViewHolder.renderUI() {
    constraintLayout.subviews(
        imageView,
        textViewTitle
    )

    imageView
        .height(utils.variable.displayWidth * .42F)
        .width(utils.variable.displayWidth * .42F)
        .constrainTopToTopOf(constraintLayout, (utils.variable.displayWidth * .43F).toInt())
        .constrainLeftToLeftOf(constraintLayout)
        .constrainRightToRightOf(constraintLayout)

    textViewTitle
        .fillHorizontally(60)
        .constrainTopToBottomOf(imageView,8)

}

fun PurchasesEmptyViewHolder.initialize(text: String) {
    textViewTitle.text = text
}
