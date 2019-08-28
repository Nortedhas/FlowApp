package com.example.ageone.Modules.SetsFilter.rows

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.Application.utils
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.View.BaseView
import yummypets.com.stevia.*

class SetsFilterRuneViewHolder(val constraintLayout: ConstraintLayout) : BaseViewHolder(constraintLayout), View.OnClickListener{
    override fun onClick(p0: View?) {

    }

    val checkColor = Color.WHITE
    val uncheckColor = Color.rgb(0xD5,0xC9,0xF1)

    val back by lazy {
        val view = BaseView()
        view.cornerRadius = 8.dp
        view.initialize()
        view
    }

    var isChecked = false

    val imageViewRune by lazy {
        val imageViewRune = BaseImageView()
        imageViewRune
    }

    init {
        /*constraintLayout.setOnClickListener {
            if (isChecked) {
                back.backgroundColor = uncheckColor
            } else {
                back.backgroundColor = checkColor
            }
            back.initialize()
            isChecked = !isChecked
        }*/
        renderUI()
    }

    fun setBack() {
        if (isChecked) {
            back.backgroundColor = checkColor
        } else {
            back.backgroundColor = uncheckColor
        }
        back.initialize()
    }
}

fun SetsFilterRuneViewHolder.renderUI() {
    constraintLayout.subviews(
        back,
        imageViewRune
    )

    back
        .constrainTopToTopOf(constraintLayout, 16)
        .constrainRightToRightOf(constraintLayout, 8)
        .width((utils.variable.displayWidth - 90) / 4)
        .height(utils.variable.displayWidth * .29F)

    imageViewRune
        .constrainCenterYToCenterYOf(back)
        .constrainCenterXToCenterXOf(back)
        .width((utils.variable.displayWidth - 90) / 4 - 20)
        .height(utils.variable.displayWidth * .17F)
}

fun SetsFilterRuneViewHolder.initialize(rune: Int, isChecked: Boolean) {
    imageViewRune.setBackgroundResource(rune)
    back.backgroundColor = if (isChecked) checkColor else uncheckColor
    back.initialize()

}
