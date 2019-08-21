package com.example.ageone.UIComponents.ViewHolders

import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextInputLayout.BaseTextInputLayout
import com.example.ageone.External.Base.TextInputLayout.InputEditTextType
import com.google.android.material.textfield.TextInputLayout
import yummypets.com.stevia.*

class InputViewHolder(constraintLayout: ConstraintLayout): BaseViewHolder(constraintLayout) {
    val textInputL by lazy {
        val textInput = BaseTextInputLayout()
        textInput.boxStrokeColor = Color.parseColor("#707ABA")
        textInput.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_FILLED)
        textInput.setInactiveUnderlineColor(Color.GRAY)
        textInput.editText?.textColor = Color.parseColor("#333333")
        textInput.editText?.textSize = 15F
        textInput
    }

    init {

        constraintLayout.subviews(
            textInputL
        )

        textInputL
            .constrainTopToTopOf(constraintLayout, 32)
            .fillHorizontally(16)
    }
}

fun InputViewHolder.initialize(hint: String, type: InputEditTextType) {
    textInputL.hint = hint
    textInputL.defineType(type)
}