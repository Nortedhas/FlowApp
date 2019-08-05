package com.example.ageone.Modules.Accaunt

import android.graphics.Color
import com.example.ageone.External.Base.Button.BaseButton
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.TextInputLayout.getPasswordInputBordered
import com.example.ageone.External.Base.TextInputLayout.getSimpleTextInputLayoutBordered
import com.example.ageone.External.Base.TextInputLayout.getSimpleTextInputLayoutUnderlined
import com.example.ageone.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*

class AccauntView: BaseModule() {

    init {
        setBackgroundColor(Color.LTGRAY)

        val btn = BaseButton()
        val textView = BaseTextView()
        val image = BaseImageView()

        val textInputLayout =
            getSimpleTextInputLayoutUnderlined("hiiii", Color.BLACK, 15f, Color.GREEN)

        val passTextInputLayout =
            getPasswordInputBordered("Enter password", Color.DKGRAY, 25F, Color.BLUE)

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
