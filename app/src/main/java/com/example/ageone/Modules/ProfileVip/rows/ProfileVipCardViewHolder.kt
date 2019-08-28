package com.example.ageone.Modules.ProfileVip.rows

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.Application.R
import com.example.ageone.Application.utils
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextView.BaseTextView
import com.example.ageone.External.Base.View.BaseView
import yummypets.com.stevia.*

class ProfileVipCardViewHolder(val constraintLayout: ConstraintLayout) : BaseViewHolder(constraintLayout) {

    val back by lazy {
        val view = BaseView()
        view.backgroundColor = Color.WHITE
        view.cornerRadius = 8.dp
        view.initialize()
        view.elevation = 3F.dp
        view
    }


    val image by lazy {
        val base = BaseView()
        base.setBackgroundResource(R.drawable.ic_go)
        base
    }

    val tape by lazy {
        val base = BaseView()
        base.setBackgroundColor(Color.parseColor("#DB9CEA"))
        base
            .height(25)
            .width(100)
        base
    }

    val textViewPrice by lazy {
        val textView = BaseTextView()
        textView.text = "-30%"
        textView.textColor = Color.WHITE
        textView.textSize = 15F
        textView.gravity = Gravity.CENTER
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }

    val textViewTitle by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.rgb(0x70,0x7A,0xBA)
        textView.textSize = 15F
        textView.gravity = Gravity.CENTER
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }

    val textViewDescribe by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.parseColor("#979797")
        textView.textSize = 11F
        textView.gravity = Gravity.CENTER
        textView.typeface = Typeface.DEFAULT
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }

    val price by lazy {
        val button = BaseTextView()
        button.textColor = Color.WHITE
        button.textSize = 11F
        button.gravity = Gravity.CENTER
        button.typeface = Typeface.DEFAULT_BOLD
        button.backgroundColor = Color.rgb(0x70,0x7A,0xBA)
        button.cornerRadius = 90
        button.initialize()
        button
    }

    init {

        renderUI()
    }
}

fun ProfileVipCardViewHolder.renderUI() {
    constraintLayout.subviews(
        back.subviews(
            image,
            tape.subviews(
                textViewPrice
            ),
            textViewTitle,
            textViewDescribe,
            price
        )
    )

    back
        .fillHorizontally(8)
        .constrainTopToTopOf(constraintLayout,8)
        .constrainBottomToBottomOf(constraintLayout, 8)

    image
        .width(75)
        .height(75)
        .constrainTopToTopOf(back, 8)
        .constrainRightToRightOf(back)
        .constrainLeftToLeftOf(back)

    textViewPrice
        .centerInParent()

    textViewTitle
        .fillHorizontally(10)
        .constrainTopToBottomOf(image, 8)

    textViewDescribe
        .fillHorizontally(10)
        .constrainTopToBottomOf(textViewTitle, 4)

    price
        .width(82)
        .height(23)
        .constrainTopToBottomOf(textViewDescribe, 16)
        .constrainLeftToLeftOf(back)
        .constrainRightToRightOf(back)
        .constrainBottomToBottomOf(back, 8)

    tape
        .rotation = 315F

    val w = (utils.variable.displayWidth - 48 ) / 2 - 75
    tape
        .constrainTopToTopOf(back, 11)
        .constrainRightToRightOf(back, w)
}

fun ProfileVipCardViewHolder.initialize(image: Int, title: String, describe: String, price: String) {
    this.image.setBackgroundResource(image)
    textViewTitle.text = title
    textViewDescribe.text = describe
    this.price.text = price

    textViewTitle.setLines(2)
    textViewDescribe.setLines(3)
}
