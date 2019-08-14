package com.example.ageone.Modules.Auth.rows

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.External.Base.ViewHolder.BaseViewHolder
import yummypets.com.stevia.subviews

class AuthButtonPhoneViewHolder (itemView: View) : BaseViewHolder(itemView) {

    init {
        if (itemView is ConstraintLayout) {
            constraintLayout = itemView
            renderUI()
        }
    }

}

fun AuthButtonPhoneViewHolder.renderUI() {

    constraintLayout?.let { constraintLayout ->
        constraintLayout.subviews(

        )

    }

}

fun AuthButtonPhoneViewHolder.initialize(textPhone: String) {


}
