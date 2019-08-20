package com.example.ageone.Modules.Registration.rows

import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextInputLayout.BaseTextInputLayout
import com.example.ageone.External.Base.TextInputLayout.InputEditTextType
import com.google.android.material.textfield.TextInputLayout
import yummypets.com.stevia.*

class RegistrationInputViewHolder(constraintLayout: ConstraintLayout): BaseViewHolder(constraintLayout) {
    val textInputL by lazy {
        val textInput = BaseTextInputLayout()
        textInput.boxStrokeColor = Color.parseColor("#707ABA")
        textInput.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_FILLED)
        textInput.setInactiveUnderlineColor(Color.GREEN)
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

fun RegistrationInputViewHolder.initialize(hint: String, type: InputEditTextType) {
    textInputL.hint = hint
    textInputL.defineType(type)
}