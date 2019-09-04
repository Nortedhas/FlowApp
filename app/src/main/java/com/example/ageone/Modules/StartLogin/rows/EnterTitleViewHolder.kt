package com.example.ageone.Modules.StartLogin.rows

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*

class EnterTitleViewHolder(val constraintLayout: ConstraintLayout): BaseViewHolder(constraintLayout){

    val textViewHello by lazy {
        val textViewHello = BaseTextView()
        textViewHello.gravity = Gravity.CENTER
        textViewHello.typeface = Typeface.DEFAULT_BOLD
        textViewHello.textSize = 29F
        textViewHello.textColor = Color.WHITE
        textViewHello.setBackgroundColor(Color.TRANSPARENT)
        textViewHello
    }

    val textViewSmall by lazy {
        val textViewSmall = BaseTextView()
        textViewSmall.gravity = Gravity.CENTER
        textViewSmall.typeface = Typeface.DEFAULT
        textViewSmall.textSize = 19F
        textViewSmall.textColor = Color.WHITE
        textViewSmall.setBackgroundColor(Color.TRANSPARENT)
        textViewSmall
    }

    val imageView by lazy {
        val imageView = BaseImageView()
        imageView
            .width(96)
            .height(95)
        imageView
    }

    init {
        renderUI()
    }

}

fun EnterTitleViewHolder.renderUI() {
    constraintLayout.subviews(
        imageView,
        textViewHello,
        textViewSmall
    )

    imageView
        .constrainLeftToLeftOf(constraintLayout)
        .constrainRightToRightOf(constraintLayout)
        .constrainTopToTopOf(constraintLayout, 56)

    textViewHello
        .constrainLeftToLeftOf(constraintLayout)
        .constrainRightToRightOf(constraintLayout)
        .constrainTopToBottomOf(imageView, 41)

    textViewSmall
        .constrainLeftToLeftOf(constraintLayout)
        .constrainRightToRightOf(constraintLayout)
        .constrainTopToBottomOf(textViewHello, 30)
}

fun EnterTitleViewHolder.initialize(textHello: String, textSmall: String, image: Int) {
    imageView.setImageResource(image)
    imageView.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN)

    textViewHello.text = textHello
    textViewSmall.text = textSmall
}