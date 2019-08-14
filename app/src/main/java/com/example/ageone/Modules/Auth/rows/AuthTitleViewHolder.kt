package com.example.ageone.Modules.Auth.rows

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.TextView.BaseTextView
import com.example.ageone.External.Base.ViewHolder.BaseViewHolder
import yummypets.com.stevia.*


class AuthTitleViewHolder(itemView: View) : BaseViewHolder(itemView) {

    val imageView by lazy {
        val imageView = BaseImageView()
        imageView
            .width(96)
            .height(95)
        imageView
    }

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

    init {
        if (itemView is ConstraintLayout) {
            constraintLayout = itemView
            renderUI()
        }
    }

}

fun AuthTitleViewHolder.renderUI() {
    constraintLayout?.let { constraintLayout ->
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

}

fun AuthTitleViewHolder.initialize(image: Int, textHello: String, textSmall: String) {
    imageView.setImageResource(image)
    imageView.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN)

    textViewHello.text = textHello
    textViewSmall.text = textSmall
}
