package com.example.ageone.External.Libraries.Glide

import android.app.Activity
import android.content.Context
import android.graphics.drawable.LayerDrawable
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.module.AppGlideModule
import com.example.ageone.Application.R
import com.example.ageone.Application.currentActivity
import yummypets.com.stevia.dp

@GlideModule
class MyGlideModule : AppGlideModule()

fun addImageFromGlide(image: ImageView, uri: String) {
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
        .with(image)
        .load(uri)
        .transform(CenterCrop(), RoundedCorners(25F.dp.toInt()))
        .placeholder(placeholder)
        .into(image)

}