package com.example.ageone.Modules.Password

import android.content.Context
import android.graphics.Color
import android.widget.Button
import com.example.ageone.External.Base.Button.BaseButton
import com.example.ageone.External.Base.Module.BaseModule
import yummypets.com.stevia.constrainTopToBottomOf
import yummypets.com.stevia.subviews

class PasswordView(context: Context?): BaseModule(/*context*/) {

    init {
        setBackgroundColor(Color.GREEN)
        val btn = BaseButton()
        innerContent.subviews(
            btn
        )
        btn.text = "Back"
        btn.constrainTopToBottomOf(toolBar, 8)


        btn.setOnClickListener {
            emitEvent?.invoke(PasswordViewModel.EventType.OnBackPressed.toString())
        }
    }
}