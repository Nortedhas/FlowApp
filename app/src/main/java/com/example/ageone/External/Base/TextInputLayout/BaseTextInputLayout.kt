package com.example.ageone.External.Base.TextInputLayout

import android.content.res.ColorStateList
import android.graphics.Color
import android.text.InputType
import android.text.method.DigitsKeyListener
import com.example.ageone.Application.R
import com.example.ageone.Application.currentActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.redmadrobot.inputmask.MaskedTextChangedListener
import yummypets.com.stevia.style

class BaseTextInputLayout: TextInputLayout(currentActivity) {

    init {
        style {
            setHintTextAppearance(R.style.MyHintText)
            setErrorTextAppearance(R.style.ErrorText)
        }
    }

    fun initPassword(colorToggled: Int = boxStrokeColor) {
        editText?.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

        style {
            isPasswordVisibilityToggleEnabled = true
            setPasswordVisibilityToggleTintList(ColorStateList.valueOf(colorToggled))
        }
    }
}

class BaseTextInputEditText: TextInputEditText(currentActivity) {

    fun defineType (type: InputEditTextType) = when(type) {

        InputEditTextType.EMAIL -> {
            inputType = InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS
        }

        InputEditTextType.NUMERIC -> {
            inputType = InputType.TYPE_CLASS_NUMBER
        }

        InputEditTextType.URI -> {
            inputType = InputType.TYPE_TEXT_VARIATION_URI
        }

        InputEditTextType.PHONE -> {
            inputType = InputType.TYPE_CLASS_NUMBER
            keyListener = DigitsKeyListener.getInstance("1234567890+-() ")

            val listener = MaskedTextChangedListener("+7 ([000]) [000]-[00]-[00]", this)

            addTextChangedListener(listener)
            onFocusChangeListener = listener
        }

        InputEditTextType.TEXT -> {}
    }

    fun setInactiveUnderlineColor(color: Int) {
        backgroundTintList = ColorStateList.valueOf(color)
    }
}

enum class InputEditTextType{
    TEXT, NUMERIC, EMAIL, URI, PHONE;
}