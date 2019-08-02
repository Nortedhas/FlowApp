package com.example.ageone.External.Base.View

import android.graphics.drawable.GradientDrawable
import android.view.View
import com.example.ageone.Application.currentActivity

class BaseView: View(currentActivity) {
    var shape = GradientDrawable()
    var background: Int? = null
    var gradient: Int? = null
    var orientation: GradientDrawable.Orientation? = null
    var cornerRadius: Int? = null
    var borderColor: Int? = null
    var borderWidth: Int? = null

    fun initialize() {
        cornerRadius?.let { cornerRadius ->
            shape.cornerRadius = cornerRadius.toFloat()
        }
        borderWidth?.let { borderWidth ->
            borderColor?.let { borderColor ->
                shape.setStroke(borderWidth, borderColor)
            }
        }
        background?.let { background ->
            shape.setColor(background)
            gradient?.let { gradient ->
                shape.setColors(intArrayOf(background, gradient))
            }
        }
        orientation?.let { orientation ->
            shape.orientation = orientation
        }
//    setBackground(shape)

//        elevation = 5f
//        translationZ = 5f
//        z = 50f

    }
}

