package com.example.ageone.External.Base.ImageView

import android.widget.ImageView
import com.example.ageone.Application.currentActivity

class BaseImageView: ImageView(currentActivity) {

}

/*
val image by lazy {
    val image = ShadowImageView(currentActivity as Context)
    image
        .height(40F.dp)
        .width(40F.dp)
        .setBackgroundColor(Color.RED)
    image
}*/
