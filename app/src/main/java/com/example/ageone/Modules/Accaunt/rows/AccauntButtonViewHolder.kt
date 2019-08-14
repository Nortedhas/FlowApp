package com.example.ageone.Modules.Accaunt.rows

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.External.Base.Button.BaseButton
import com.example.ageone.External.Base.ViewHolder.BaseViewHolder
import yummypets.com.stevia.fillHorizontally
import yummypets.com.stevia.subviews


class AccauntButtonViewHolder(itemView: View) : BaseViewHolder(itemView) {

    val button by lazy {
        val btn = BaseButton()
        btn
    }

    init {
        if (itemView is ConstraintLayout) {
            constraintLayout = itemView
            renderUI()
        }
    }

}

fun AccauntButtonViewHolder.renderUI() {
    constraintLayout?.subviews(button)
    button.fillHorizontally()
}

fun AccauntButtonViewHolder.initialize(text: String) {
    button.text = text
}
