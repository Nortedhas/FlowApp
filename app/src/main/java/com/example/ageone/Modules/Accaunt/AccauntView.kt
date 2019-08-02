package com.example.ageone.Modules.Accaunt

import android.graphics.Color
import com.example.ageone.Application.currentActivity
import com.example.ageone.External.Base.Button.BaseButton
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.TextView.BaseTextView
import yummypets.com.stevia.constrainTopToBottomOf
import yummypets.com.stevia.subviews

class AccauntView: BaseModule(currentActivity) {

    init {
        setBackgroundColor(Color.LTGRAY)
        val btn = BaseButton()
        val textView = BaseTextView(currentActivity)

        innerContent.subviews(
            btn,
            textView
        )
        btn.text = "Some"

        textView.text = "Accaunt"

        btn.constrainTopToBottomOf(toolBar, 8)

        btn.setOnClickListener {
            emitEvent?.invoke(AccauntViewModel.EventType.OnPhotoClicked.toString())
        }

    }

}