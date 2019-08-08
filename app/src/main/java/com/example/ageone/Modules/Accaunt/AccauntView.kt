package com.example.ageone.Modules.Accaunt

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import com.example.ageone.External.Base.Button.BaseButton
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.TextInputLayout.BaseTextInputLayout
import com.example.ageone.External.Base.TextInputLayout.InputEditTextType
import com.example.ageone.External.Base.TextView.BaseTextView
import com.example.ageone.External.Libraries.Glide.addImageFromGlide
import com.google.android.material.textfield.TextInputLayout
import yummypets.com.stevia.*
import android.R
import android.content.Context
import android.graphics.Outline
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.ImageView
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.target.ViewTarget
import com.bumptech.glide.request.transition.Transition
import com.example.ageone.Application.currentActivity
import com.example.ageone.External.Libraries.Glide.GlideApp
import com.example.ageone.External.Libraries.Glide.addImageFromGlideWithShadow
import io.github.armcha.coloredshadow.ShadowImageView


class AccauntView: BaseModule() {

    init {
        setBackgroundColor(Color.LTGRAY)

        val btn = BaseButton()
        val textView = BaseTextView()
        val image = ShadowImageView(currentActivity as Context)

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

        val text by lazy {
            val text = BaseTextView()
            text.text = "elevation"
            val shape = GradientDrawable()
            shape.shape = GradientDrawable.RECTANGLE
            shape.setColor(Color.parseColor("#30bcff"))
            text.background = shape
            text.width(25F.dp)
            text.elevation = 8F.dp
            text
        }

        innerContent.subviews(
            btn,
            textView,
            image,
            textInputL,
            textInputPassword,
            text
        )

        btn.text = "Some"
        textView.text = "Accaunt"
        image
            .height(40F.dp)
            .width(40F.dp)
            .setBackgroundColor(Color.TRANSPARENT)

        addImageFromGlideWithShadow(image, "https://i.pinimg.com/originals/8c/d2/f5/8cd2f5f7c3b02db7bf60b5ec68d11398.jpg")

        btn.constrainTopToBottomOf(toolBar, 8)
        textView.constrainTopToBottomOf(btn, 8)
        image
            .constrainTopToBottomOf(textView, 8)

        textInputL
            .fillHorizontally()
        textInputL.constrainTopToBottomOf(image, 8)

        textInputPassword
            .fillHorizontally()
        textInputPassword.constrainTopToBottomOf(textInputL, 8)

        text.constrainTopToBottomOf(textInputPassword, 8)

        btn.setOnClickListener {
            emitEvent?.invoke(AccauntViewModel.EventType.OnPhotoClicked.toString())
        }
    }

}
