package com.example.ageone.Modules.Accaunt

import android.graphics.Color
import com.example.ageone.External.Base.Button.BaseButton
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.TextInputLayout.InputEditTextType
import com.example.ageone.External.Base.TextInputLayout.InputLayoutType
import com.example.ageone.External.Base.TextInputLayout.getSimpleTextInputLayout
import com.example.ageone.External.Base.TextInputLayout.withText
import com.example.ageone.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*

class AccauntView: BaseModule() {

    init {
        setBackgroundColor(Color.LTGRAY)

        val btn = BaseButton()
        val textView = BaseTextView()
        val image = BaseImageView()

        val textInputLayout =
            getSimpleTextInputLayout("hiiii", Color.BLACK, 15f, Color.GREEN,
                inputLayoutType = InputLayoutType.BORDERED).withText()

        val passTextInputLayout =
            getSimpleTextInputLayout("Enter password",
                activeHintColor = Color.RED,
                cornerRadius = 5F,
                backgroundColor = Color.WHITE,
                borderColor = Color.YELLOW,
                inputLayoutType = InputLayoutType.UNDERLINED).withText(isPassword = true)

        val numericTextInputLayout =
            getSimpleTextInputLayout("Enter numeric",
                activeHintColor = Color.RED,
                cornerRadius = 5F,
                backgroundColor = Color.WHITE,
                borderColor = Color.YELLOW,
                inputLayoutType = InputLayoutType.UNDERLINED).withText(inputType = InputEditTextType.PHONE)

        val emailTextInputLayout =
            getSimpleTextInputLayout("Enter email",
                activeHintColor = Color.RED,
                cornerRadius = 5F,
                backgroundColor = Color.WHITE,
                borderColor = Color.YELLOW,
                inputLayoutType = InputLayoutType.UNDERLINED).withText(inputType = InputEditTextType.EMAIL)

        val uriTextInputLayout =
            getSimpleTextInputLayout("Enter uri",
                activeHintColor = Color.RED,
                backgroundColor = Color.WHITE,
                borderColor = Color.YELLOW,
                inputLayoutType = InputLayoutType.UNDERLINED).withText(inputType = InputEditTextType.URI)

        innerContent.subviews(
            btn,
            textView,
            image,
            textInputLayout,
            passTextInputLayout,
            numericTextInputLayout,
            emailTextInputLayout,
            uriTextInputLayout
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

        numericTextInputLayout
            .fillHorizontally()
        numericTextInputLayout.constrainTopToBottomOf(passTextInputLayout, 8)

        emailTextInputLayout
            .fillHorizontally()
        emailTextInputLayout.constrainTopToBottomOf(numericTextInputLayout, 8)

        uriTextInputLayout
            .fillHorizontally()
        uriTextInputLayout.constrainTopToBottomOf(emailTextInputLayout, 8)


        btn.setOnClickListener {
            emitEvent?.invoke(AccauntViewModel.EventType.OnPhotoClicked.toString())
        }
    }

}
