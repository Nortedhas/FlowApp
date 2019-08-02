package com.example.ageone.Modules.Accaunt

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.LayerDrawable
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.ageone.Application.R
import com.example.ageone.Application.currentActivity
import com.example.ageone.External.Base.Button.BaseButton
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.TextView.BaseTextView
import com.example.ageone.External.Libraries.Glide.GlideApp
import yummypets.com.stevia.*
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.Priority


class AccauntView: BaseModule(currentActivity) {

    init {
        setBackgroundColor(Color.LTGRAY)

        val btn = BaseButton()
        val textView = BaseTextView()
        val image = BaseImageView()

        innerContent.subviews(
            btn,
            textView,
            image
        )

        btn.text = "Some"
        textView.text = "Accaunt"
        image
            .height(40F.dp)
            .width(40F.dp)

        val circularProgressDrawable = CircularProgressDrawable(currentActivity as Context)
        circularProgressDrawable.strokeWidth = 15f.dp
        circularProgressDrawable.centerRadius = 100f.dp
        circularProgressDrawable.start()

        val placeholderImage = (currentActivity as Activity).resources.getDrawable(R.drawable.kitty)
        placeholderImage

        val placeholder = LayerDrawable(
            arrayOf(
                placeholderImage,
                circularProgressDrawable
            )
        )

        //for cashing photo
        /*val requestOptions = RequestOptions().priority(Priority.IMMEDIATE)
        GlideApp
            .with(this)
            .downloadOnly()
            .apply(requestOptions)
            .submit()*/

        GlideApp
            .with(this)
            .load("")
            .transform(CenterCrop(), RoundedCorners(25F.dp.toInt()))
            .placeholder(placeholder)
            .into(image)


        btn.constrainTopToBottomOf(toolBar, 8)
        textView.constrainTopToBottomOf(btn, 8)
        image.constrainTopToBottomOf(textView, 8)



        btn.setOnClickListener {
            emitEvent?.invoke(AccauntViewModel.EventType.OnPhotoClicked.toString())
        }

    }

}