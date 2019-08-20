package com.example.ageone.Modules.StartLogin.rows

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.Application.R
import com.example.ageone.External.Base.Button.BaseButton
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextView.BaseTextView
import com.example.ageone.External.Base.View.BaseView
import yummypets.com.stevia.*

class EnterButtonViewHolder(constraintLayout: ConstraintLayout): BaseViewHolder(constraintLayout) {
    val rectangleUp by lazy {
        val view = BaseView()

        view.backgroundColor = Color.WHITE
        view.cornerRadius = 90
        view.initialize()

        view
    }

    val rectangleDown by lazy {
        val view = BaseView()

        view.backgroundColor = Color.WHITE
        view.initialize()

        view
    }

    val textViewEnter by lazy {
        val textViewHello = BaseTextView()

        textViewHello.gravity = Gravity.CENTER
        textViewHello.typeface = Typeface.DEFAULT_BOLD
        textViewHello.textSize = 21F
        textViewHello.textColor = Color.parseColor("#707ABA")
        textViewHello.setBackgroundColor(Color.TRANSPARENT)

        textViewHello
    }

    val buttonEnterPhone by lazy {
        val button = BaseButton()

        button.textSize = 17F
        button.textColor = Color.WHITE
        button.typeface = Typeface.DEFAULT

        button.backgroundColor = Color.parseColor("#8B91C7")
        button.cornerRadius = 60
        button.imageIcon = R.drawable.phone//sizeIcon???

        button.initialize()
        button
    }

    init {
        constraintLayout.subviews(
            rectangleUp,
            rectangleDown,
            textViewEnter,
            buttonEnterPhone
        )

        rectangleUp
            .fillHorizontally()
            .constrainTopToTopOf(constraintLayout, 48)
            .height(90)

        textViewEnter
            .fillHorizontally()
            .constrainTopToTopOf(rectangleUp, 16)

        buttonEnterPhone
            .constrainLeftToLeftOf(constraintLayout)
            .constrainRightToRightOf(constraintLayout)
            .constrainTopToBottomOf(textViewEnter, 24)
            .fillHorizontally(40)

        rectangleDown
            .constrainBottomToBottomOf(buttonEnterPhone)
            .fillHorizontally()
            .height(60)

    }
}

fun EnterButtonViewHolder.initialize(textEnter: String, textPhone: String) {
    textViewEnter.text = textEnter
    buttonEnterPhone.text = textPhone
}