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

        val text = BaseTextInputEditText()
        addView(text)
    }

    fun initPassword(colorToggled: Int = boxStrokeColor) {
        editText?.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

        style {
            isPasswordVisibilityToggleEnabled = true
            setPasswordVisibilityToggleTintList(ColorStateList.valueOf(colorToggled))
        }
    }


    fun defineType (type: InputEditTextType) = when(type) {

        InputEditTextType.EMAIL -> {
            editText?.inputType = InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS
        }

        InputEditTextType.NUMERIC -> {
            editText?.inputType = InputType.TYPE_CLASS_NUMBER
        }

        InputEditTextType.URI -> {
            editText?.inputType = InputType.TYPE_TEXT_VARIATION_URI
        }

        InputEditTextType.PHONE -> {
            editText?.inputType = InputType.TYPE_CLASS_NUMBER
            editText?.keyListener = DigitsKeyListener.getInstance("1234567890+-() ")

            editText?.let { editText ->
                val listener = MaskedTextChangedListener("+7 ([000]) [000]-[00]-[00]", editText)
                editText.addTextChangedListener(listener)

                onFocusChangeListener = listener
            }

        }

        InputEditTextType.TEXT -> {}
    }

    fun setInactiveUnderlineColor(color: Int) {
        editText?.backgroundTintList = ColorStateList.valueOf(color)
    }

}

class BaseTextInputEditText: TextInputEditText(currentActivity) {
}

enum class InputEditTextType{
    TEXT, NUMERIC, EMAIL, URI, PHONE;
}