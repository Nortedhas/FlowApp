package com.example.ageone.Modules.Auth

import android.graphics.Color
import com.example.ageone.External.Base.Button.BaseButton
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.Models.User.user
import timber.log.Timber
import yummypets.com.stevia.constrainTopToBottomOf
import yummypets.com.stevia.constrainTopToTopOf
import yummypets.com.stevia.subviews

class AuthView: BaseModule() {

    init {
        setBackgroundColor(Color.LTGRAY)
        val btn = BaseButton()
        val btnNav = BaseButton()

        innerContent.subviews(
            btn,
            btnNav
        )
        btn.text = "Some"
        btnNav.text = "Open nav"

        btn
//            .constrainTopToBottomOf(toolBar, 8)
            .constrainTopToTopOf(innerContent)

        btnNav.constrainTopToBottomOf(btn, 8)

        btn.setOnClickListener {
            emitEvent?.invoke(AuthViewModel.EventType.OnButtonPressed.toString())
        }

        btnNav.setOnClickListener {
            user.isAuthorized = true
            Timber.i("${user.isAuthorized}")
            emitEvent?.invoke(AuthViewModel.EventType.OnButonOpenNavPressed.toString())
        }

    }

}