package com.example.ageone.Modules.Accaunt.rows

import android.graphics.Color
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.External.Base.TextInputLayout.BaseTextInputLayout
import com.example.ageone.External.Base.ViewHolder.BaseViewHolder
import com.google.android.material.textfield.TextInputLayout
import yummypets.com.stevia.fillHorizontally
import yummypets.com.stevia.subviews


class AccauntNameViewHolder(itemView: View) : BaseViewHolder(itemView) {

    val textInputName by lazy {
        val textInputL = BaseTextInputLayout()
        textInputL.boxStrokeColor = Color.MAGENTA
        textInputL.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_FILLED)
        textInputL
    }

    init {
        if (itemView is ConstraintLayout) {
            constraintLayout = itemView
            renderUI()
        }
    }

}


fun AccauntNameViewHolder.renderUI() {
    constraintLayout?.subviews(textInputName)
    textInputName.fillHorizontally()
}

fun AccauntNameViewHolder.initialize(hint: String) {
    textInputName.hint = hint
}
