package com.example.ageone.Modules.Auth

import android.graphics.Color
import com.example.ageone.External.Base.Button.BaseButton
import com.example.ageone.External.Base.Module.BaseModule
import yummypets.com.stevia.constrainTopToBottomOf
import yummypets.com.stevia.constrainTopToTopOf
import yummypets.com.stevia.subviews

class OneView: BaseModule() {

    init {
        setBackgroundColor(Color.LTGRAY)
        val btn = BaseButton()

        innerContent.subviews(
            btn
        )
        btn.text = "Hello one"

        btn
//            .constrainTopToBottomOf(toolBar, 8)
            .constrainTopToTopOf(innerContent)


        btn.setOnClickListener {
            emitEvent?.invoke(OneViewModel.EventType.OnButtonPressed.toString())
        }

    }

}