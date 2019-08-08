package com.example.ageone.Modules.Password

import android.graphics.Color
import com.example.ageone.External.Base.Button.BaseButton
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.Module.BaseModule
import yummypets.com.stevia.*

class PasswordView: BaseModule() {

    init {
        setBackgroundColor(Color.GREEN)
        val btn = BaseButton()
        val image = BaseImageView()
        innerContent.subviews(
            btn,
            image
        )
        btn.text = "Back"
        btn.constrainTopToBottomOf(toolBar, 8)


        btn.setOnClickListener {
            emitEvent?.invoke(PasswordViewModel.EventType.OnBackPressed.toString())
        }

        image
            .width(40F.dp)
            .height(40F.dp)
            .backgroundColor = Color.BLUE

        image.constrainTopToBottomOf(btn, 8)
    }
}