package com.example.ageone.Modules.Accaunt

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.text.InputType
import android.widget.EditText
import com.example.ageone.Application.R
import com.example.ageone.Application.currentActivity
import com.example.ageone.External.Base.Button.BaseButton
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*
import com.example.ageone.External.Libraries.Glide.addImageFromGlide
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textfield.TextInputEditText

class AccauntView: BaseModule() {

    init {
        setBackgroundColor(Color.LTGRAY)

        val btn = BaseButton()
        val textView = BaseTextView()
        val image = BaseImageView()

        val textInputLayout = TextInputLayout(currentActivity)
        textInputLayout.style {
            hint = "Please Enter Email Address"
            setHintTextAppearance(R.style.MyHintText)
            defaultHintTextColor = ColorStateList.valueOf(Color.GREEN)

            setErrorTextAppearance(R.style.MyHintText)

            setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE)
            setBoxCornerRadii(5f,5f,
                5f,5f)

        }

        val edtEmail = TextInputEditText(textInputLayout.context)
        edtEmail.style{
            textSize = 14F
            textColor = Color.GREEN
//            setHintTextColor(Color.GREEN)
        }
        textInputLayout.addView(edtEmail)

        val passTextInputLayout = TextInputLayout(currentActivity)
        passTextInputLayout.style {
            hint = "Please Enter Password"
            setHintTextAppearance(R.style.MyHintText)
            setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_FILLED)
            setBoxCornerRadii(5f, 5f,
                5f, 5f)
            isPasswordVisibilityToggleEnabled = true

            boxBackgroundColor = Color.WHITE
            setPasswordVisibilityToggleTintList(ColorStateList.valueOf(Color.YELLOW))
        }

        val edtPass = TextInputEditText(passTextInputLayout.context)
        edtPass.style {
            inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            background?.mutate()?.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP)
        }
        passTextInputLayout.addView(edtPass)

        innerContent.subviews(
            btn,
            textView,
            image,
            textInputLayout,
            passTextInputLayout
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

        textInputLayout
            .fillHorizontally()
        textInputLayout.constrainTopToBottomOf(image, 8)

        passTextInputLayout
            .fillHorizontally()
        passTextInputLayout.constrainTopToBottomOf(textInputLayout, 8)


        btn.setOnClickListener {
            emitEvent?.invoke(AccauntViewModel.EventType.OnPhotoClicked.toString())
        }
    }

}
