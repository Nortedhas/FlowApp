package com.example.ageone.Modules.Registration.rows

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextView.BaseTextView
import com.example.ageone.External.Base.TextView.underline
import yummypets.com.stevia.*

class RegistrationTextHolder(val constraintLayout: ConstraintLayout): BaseViewHolder(constraintLayout) {

    val textView by lazy {
        val textViewLogin = BaseTextView()
        textViewLogin.textColor = Color.rgb(0x8A,0x8A,0x8F)
        textViewLogin.textSize = 15F
        textViewLogin.gravity = Gravity.CENTER
        textViewLogin.typeface = Typeface.DEFAULT
        textViewLogin.setBackgroundColor(Color.TRANSPARENT)
        textViewLogin
    }

    val textViewLink by lazy {
        val textViewLogin = BaseTextView()
        textViewLogin.textColor = Color.rgb(0x70, 0x7A, 0xBA)
        textViewLogin.textSize = 15F
        textViewLogin.gravity = Gravity.CENTER
        textViewLogin.typeface = Typeface.DEFAULT
        textViewLogin.setBackgroundColor(Color.TRANSPARENT)
        textViewLogin.underline()
        textViewLogin
    }



    init {
        constraintLayout.setBackgroundColor(Color.WHITE)
        renderUI()
    }

}

fun RegistrationTextHolder.renderUI() {

    constraintLayout.subviews(
        textView,
        textViewLink
    )

    textView
        .constrainTopToTopOf(constraintLayout, 40)
        .constrainLeftToLeftOf(constraintLayout)
        .constrainRightToRightOf(constraintLayout)

    textViewLink
        .constrainTopToBottomOf(textView)
        .constrainLeftToLeftOf(constraintLayout)
        .constrainRightToRightOf(constraintLayout)


}

fun RegistrationTextHolder.initialize() {
    textView.text = "Нажимая на кнопку “Зарегистрироваться”, я соглашаюсь с данным"
    textViewLink.text = "пользовательским соглашением"
}