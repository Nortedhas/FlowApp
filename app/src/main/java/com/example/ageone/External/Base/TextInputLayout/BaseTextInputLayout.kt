package com.example.ageone.External.Base.TextInputLayout

import android.content.res.ColorStateList
import android.graphics.Color
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.InputType
import android.text.method.DigitsKeyListener
import com.example.ageone.Application.R
import com.example.ageone.Application.currentActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.redmadrobot.inputmask.MaskedTextChangedListener
import yummypets.com.stevia.style
import yummypets.com.stevia.textColor

class BaseTextInputLayout(hintMessage: String,
                          activeHintColor: Int,
                          cornerRadius: Float,
                          backgroundColor: Int = Color.TRANSPARENT,
                          borderColor: Int = Color.DKGRAY): TextInputLayout(currentActivity) {
    init {
        style {
            setHintTextAppearance(R.style.MyHintText)
            setErrorTextAppearance(R.style.ErrorText)

            hint = hintMessage
            defaultHintTextColor = ColorStateList.valueOf(activeHintColor)

            boxBackgroundColor = backgroundColor
            boxStrokeColor = borderColor

            setBoxCornerRadii(cornerRadius, cornerRadius, cornerRadius, cornerRadius)
        }
    }
}

fun getSimpleTextInputLayout(
    hintMessage: String,
    activeHintColor: Int,
    cornerRadius: Float = 5F,
    backgroundColor: Int = Color.TRANSPARENT,
    borderColor: Int = Color.DKGRAY,
    inputLayoutType: InputLayoutType = InputLayoutType.UNDERLINED
): BaseTextInputLayout {
    val textInputLayout = BaseTextInputLayout(hintMessage, activeHintColor, cornerRadius,
        backgroundColor, borderColor)

    when(inputLayoutType) {
        InputLayoutType.UNDERLINED -> textInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_FILLED)
        InputLayoutType.BORDERED -> textInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE)
    }

    return textInputLayout
}

fun BaseTextInputLayout.withText(
    colorTextInput: Int = Color.BLACK,
    sizeTextInput: Float = 14F,
    isPassword: Boolean = false,
    colorToggled: Int = colorTextInput,
    inputType: InputEditTextType = InputEditTextType.TEXT
): BaseTextInputLayout{
    val textInput = BaseTextInputEditText(sizeTextInput, colorTextInput)

    if (isPassword) {
        textInput.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

        style {
            isPasswordVisibilityToggleEnabled = true
            setPasswordVisibilityToggleTintList(ColorStateList.valueOf(colorToggled))
        }
    }

    when(inputType) {
        InputEditTextType.EMAIL -> {
            textInput.inputType = InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS
        }

        InputEditTextType.NUMERIC -> {
            textInput.inputType = InputType.TYPE_CLASS_NUMBER
        }

        InputEditTextType.URI -> {
            textInput.inputType = InputType.TYPE_TEXT_VARIATION_URI
        }

        InputEditTextType.PHONE -> {
            textInput.inputType = InputType.TYPE_CLASS_NUMBER
            textInput.keyListener = DigitsKeyListener.getInstance("1234567890+-() ")

            val listener = MaskedTextChangedListener("+7 ([000]) [000]-[00]-[00]", textInput)

            textInput.addTextChangedListener(listener)
            textInput.onFocusChangeListener = listener
        }
    }

    addView(textInput)

    return this
}

/*
fun getPasswordInputBordered(
    hintMessage: String,
    activeHintColor: Int,
    cornerRadius: Float,
    colorTextInput: Int,
    backgroundColor: Int = Color.TRANSPARENT,
    sizeTextInput: Float = 14F,
    borderColor: Int = Color.DKGRAY,
    colorToggled: Int = colorTextInput
): BaseTextInputLayout {
    val textInputLayout = BaseTextInputLayout(hintMessage, activeHintColor, cornerRadius,
        backgroundColor, borderColor)

    textInputLayout.style {
        setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE)

        isPasswordVisibilityToggleEnabled = true
        setPasswordVisibilityToggleTintList(ColorStateList.valueOf(colorToggled))
    }
    val textInput = createSimpleTextInput(colorTextInput, sizeTextInput, true)
    textInputLayout.addView(textInput)

    return textInputLayout
}

fun getSimpleTextInputLayoutUnderlined(
    hintMessage: String,
    activeHintColor: Int,
    cornerRadius: Float,
    colorTextInput: Int,
    backgroundColor: Int = Color.TRANSPARENT,
    sizeTextInput: Float = 14F
): BaseTextInputLayout {
    val textInputLayout = BaseTextInputLayout(hintMessage, activeHintColor, cornerRadius,
        backgroundColor)

    textInputLayout.style {

    }

    val textInput = createSimpleTextInput(colorTextInput, sizeTextInput)
    textInputLayout.addView(textInput)

    return textInputLayout
}
*/

/*private fun createSimpleTextInput(
    colorTextInput: Int,
    sizeTextInput: Float = 14F,
    isPassword: Boolean = false
): BaseTextInputEditText {
    val textInput = BaseTextInputEditText()

    textInput.style{
        textSize = sizeTextInput
        textColor = colorTextInput
        if (isPassword) {
            inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
    }

    return textInput
}*/

class BaseTextInputEditText(sizeTextInput: Float,
                            colorTextInput: Int): TextInputEditText(currentActivity) {
    init{
        style{
            textSize = sizeTextInput
            textColor = colorTextInput
        }
    }
}


enum class InputLayoutType {
    BORDERED, UNDERLINED;
}

enum class InputEditTextType{
    TEXT, NUMERIC, EMAIL, URI, PHONE;
}