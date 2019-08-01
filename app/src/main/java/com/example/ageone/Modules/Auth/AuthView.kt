package com.example.ageone.Modules.Auth

import android.content.Context
import android.graphics.Color
import android.widget.Button
import com.example.ageone.External.Base.Module.BaseModule
import yummypets.com.stevia.constrainTopToBottomOf
import yummypets.com.stevia.subviews

class AuthView(context: Context?): BaseModule(context) {

    init {
        setBackgroundColor(Color.LTGRAY)
        val btn = Button(context)
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