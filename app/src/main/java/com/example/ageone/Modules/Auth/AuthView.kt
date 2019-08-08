package com.example.ageone.Modules.Auth

import android.graphics.Color
import com.example.ageone.External.Base.Button.BaseButton
import com.example.ageone.External.Base.Module.BaseModule
import yummypets.com.stevia.constrainTopToBottomOf
import yummypets.com.stevia.subviews

class AuthView: BaseModule() {

    init {
        setBackgroundColor(Color.LTGRAY)
        val btn = BaseButton()

        innerContent.subviews(
            btn
        )
        btn.text = "Some"

        btn.constrainTopToBottomOf(toolBar, 8)

        btn.setOnClickListener {
            emitEvent?.invoke(AuthViewModel.EventType.OnButtonPressed.toString())
        }

    }

}