package com.example.ageone.External.Base.Button

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.widget.Button
import com.example.ageone.Application.R
import com.example.ageone.Application.currentActivity
import yummypets.com.stevia.dp

class BaseButton: Button(currentActivity) {
    val gradientDrawable = GradientDrawable()

    var cornerRadius: Int? = null
    var backgroundColor: Int? = null

    var imageIcon: Int? = null

    var sizeIconWidth = 22F
    var sizeIconHeight = 22F

    var borderColor: Int? = null
    var borderWidth: Int? = null

    fun initialize() {
        isAllCaps = false
        gravity = Gravity.CENTER
        stateListAnimator = null

        gradientDrawable.shape = GradientDrawable.RECTANGLE

        cornerRadius?.let { cornerRadius ->
            gradientDrawable.cornerRadius = cornerRadius.toFloat()
        }

        backgroundColor?.let { backgroundColor ->
            gradientDrawable.setColor(backgroundColor)
        }

        imageIcon?.let { imageIcon ->
            val phone = currentActivity?.resources?.getDrawable(imageIcon)

            val bitmap = (phone as BitmapDrawable).bitmap
            val d = BitmapDrawable(currentActivity?.resources, Bitmap.createScaledBitmap(bitmap, sizeIconWidth.dp.toInt(), sizeIconHeight.dp.toInt(), true))
            setCompoundDrawablesWithIntrinsicBounds(d, null, null, null)
            setPadding(50, 0, 10, 0)
        }

        borderWidth?.let { borderWidth ->
            borderColor?.let { borderColor ->
                gradientDrawable.setStroke(borderWidth, borderColor)
            }
        }

        background = gradientDrawable
    }
}