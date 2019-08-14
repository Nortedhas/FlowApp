package com.example.ageone.Modules.Accaunt.rows

import android.graphics.Color
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.External.Base.TextInputLayout.BaseTextInputLayout
import com.example.ageone.External.Base.TextInputLayout.InputEditTextType
import com.example.ageone.External.Base.ViewHolder.BaseViewHolder
import com.google.android.material.textfield.TextInputLayout
import yummypets.com.stevia.fillHorizontally
import yummypets.com.stevia.subviews

class AccauntPhoneViewHolder(itemView: View) : BaseViewHolder(itemView) {

    val textInputPhone by lazy {
        val textInputL = BaseTextInputLayout()
        textInputL.boxStrokeColor = Color.MAGENTA
        textInputL.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_FILLED)
        textInputL.defineType(InputEditTextType.PHONE)
        textInputL
    }

    init {
        if (itemView is ConstraintLayout) {
            constraintLayout = itemView
            renderUI()
        }
    }

}

fun AccauntPhoneViewHolder.renderUI() {
    constraintLayout?.subviews(textInputPhone)
    textInputPhone.fillHorizontally()
}

fun AccauntPhoneViewHolder.initialize(hint: String) {
    textInputPhone.hint = hint
}