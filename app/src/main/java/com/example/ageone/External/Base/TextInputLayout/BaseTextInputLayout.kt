package com.example.ageone.External.Base.TextInputLayout

import android.content.res.ColorStateList
import android.graphics.Color
import android.text.InputType
import com.example.ageone.Application.R
import com.example.ageone.Application.currentActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
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

fun getSimpleTextInputLayoutBordered(
    hintMessage: String,
    activeHintColor: Int,
    cornerRadius: Float,
    colorTextInput: Int,
    backgroundColor: Int = Color.TRANSPARENT,
    sizeTextInput: Float = 14F,
    borderColor: Int = Color.DKGRAY
): BaseTextInputLayout {
    val textInputLayout = BaseTextInputLayout(hintMessage, activeHintColor, cornerRadius,
        backgroundColor, borderColor)

    textInputLayout.style {
        setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE)
    }

    val textInput = createSimpleTextInput(colorTextInput, sizeTextInput)
    textInputLayout.addView(textInput)

    return textInputLayout
}

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

private fun createSimpleTextInput(
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
}

class BaseTextInputEditText: TextInputEditText(currentActivity) {

}