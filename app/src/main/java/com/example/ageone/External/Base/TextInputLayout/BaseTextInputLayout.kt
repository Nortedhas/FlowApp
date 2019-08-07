package com.example.ageone.External.Base.TextInputLayout

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.text.Editable
import android.text.InputFilter
import android.text.InputType
import android.text.TextWatcher
import android.text.method.DigitsKeyListener
import android.widget.EditText
import com.example.ageone.Application.R
import com.example.ageone.Application.currentActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import timber.log.Timber
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
            editText?.let{ editText ->
                editText.inputType = InputType.TYPE_CLASS_NUMBER
                editText.keyListener = DigitsKeyListener.getInstance("1234567890")
                editText.limitLength(18)


                editText.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {}

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                    @SuppressLint("SetTextI18n")
                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        Timber.i("$s start: $start before: $before count: $count")

                        if (before == 0) {
                            val newPhone = when (s?.length ?: 40) {
                                1 -> {
                                    when (s?.last() ?: '0') {
                                        '7', '8' -> {
                                            "+7"
                                        }
                                        '9' -> {
                                            "+7 (9"
                                        }
                                        else -> {
                                            ""
                                        }
                                    }
                                }

                                2 -> {
                                    when (s?.last() ?: '0') {
                                        '9' -> {
                                            "+7 (9"
                                        }
                                        else -> {
                                            "+7"
                                        }
                                    }
                                }

                                in (3..5) -> {
                                    "+7 (9"
                                }

                                7 -> {
                                    editText.text.toString() + ") "
                                }

                                8 -> {
                                    val last = editText.text?.last()
                                    editText.text.toString().dropLast(1) + ") " + last
                                }

                                9 -> {
                                    val last = editText.text?.last()
                                    editText.text.toString().dropLast(2) + ") " + last
                                }

                                13, 16 -> {
                                    val last = editText.text?.last()
                                    editText.text.toString().dropLast(1) + "-" + last
                                }

                                else -> editText.text

                            }
                            editText.setText(newPhone)
                            editText.setSelection(editText.text?.length ?: 0)
                        }
                    }
                })
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

fun EditText.limitLength(maxLength: Int) {
    filters = arrayOf(InputFilter.LengthFilter(maxLength))
}

enum class InputEditTextType{
    TEXT, NUMERIC, EMAIL, URI, PHONE;
}