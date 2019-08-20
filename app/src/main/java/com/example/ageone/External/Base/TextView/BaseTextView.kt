package com.example.ageone.External.Base.TextView

import android.graphics.Paint
import android.widget.TextView
import com.example.ageone.Application.currentActivity

class BaseTextView(): TextView(currentActivity) {

    init{

    }

}

fun TextView.underline() {
    paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
}

