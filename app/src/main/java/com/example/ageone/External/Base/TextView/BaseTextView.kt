package com.example.ageone.External.Base.TextView

import android.widget.TextView
import com.example.ageone.Application.currentActivity

class BaseTextView(): TextView(currentActivity) {

    init{

    }

}

/*
val text by lazy {
    val text = BaseTextView()
    text.text = "elevation"
    val gradientDrawable = GradientDrawable()
    gradientDrawable.gradientDrawable = GradientDrawable.RECTANGLE
    gradientDrawable.setColor(Color.parseColor("#30bcff"))
    text.backgroundColor = gradientDrawable
    text.width(25F.dp)
    text.elevation = 8F.dp
    text
}*/
