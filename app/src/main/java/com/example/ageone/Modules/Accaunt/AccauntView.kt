package com.example.ageone.Modules.Accaunt

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.View
import com.example.ageone.Application.currentActivity
import com.example.ageone.External.Base.Button.BaseButton
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.TextInputLayout.*
import com.example.ageone.External.Base.TextView.BaseTextView
import com.google.android.material.textfield.TextInputLayout
import yummypets.com.stevia.*

class AccauntView: BaseModule() {

    init {
        setBackgroundColor(Color.LTGRAY)

        val btn = BaseButton()
        val textView = BaseTextView()
        val image = BaseImageView()

        val textInputL by lazy {
            val textInputL = BaseTextInputLayout()
            textInputL.hint = "phone"
            textInputL.boxStrokeColor = Color.TRANSPARENT
            textInputL.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_FILLED)
            textInputL.defineType(InputEditTextType.PHONE)
            textInputL.setInactiveUnderlineColor(Color.GREEN)
            textInputL.editText?.textColor = Color.MAGENTA
            textInputL
        }

        val textInputPassword by lazy {
            val textInputL = BaseTextInputLayout()
            textInputL.hint = "password"
            textInputL.boxStrokeColor = Color.MAGENTA
            textInputL.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE)
            textInputL.initPassword()
            textInputL
        }

        innerContent.subviews(
            btn,
            textView,
            image,
            textInputL,
            textInputPassword
        )

        btn.text = "Some"
        textView.text = "Accaunt"
        image
            .height(40F.dp)
            .width(40F.dp)
            .setBackgroundColor(Color.RED)

//        addImageFromGlide(image, "")


        btn.constrainTopToBottomOf(toolBar, 8)
        textView.constrainTopToBottomOf(btn, 8)
        image.constrainTopToBottomOf(textView, 8)

        textInputL
            .fillHorizontally()
        textInputL.constrainTopToBottomOf(image, 8)

        textInputPassword
            .fillHorizontally()
        textInputPassword.constrainTopToBottomOf(textInputL, 8)

        btn.setOnClickListener {
            emitEvent?.invoke(AccauntViewModel.EventType.OnPhotoClicked.toString())
        }
    }

}
