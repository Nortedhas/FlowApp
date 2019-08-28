package com.example.ageone.Modules.Announce.rows

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.Application.R
import com.example.ageone.Application.utils
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextView.BaseTextView
import com.example.ageone.External.Libraries.Glide.addImageFromGlide
import yummypets.com.stevia.*

class AnnounceViewHolder(val constraintLayout: ConstraintLayout) : BaseViewHolder(constraintLayout) {

    val imageViewPhoto by lazy {
        val imageView = BaseImageView()
        imageView.cornerRadius = 8.dp
        imageView.backgroundColor = Color.parseColor("#EBEBF0")
        imageView.initialize()
//        imageView.elevation = 4F.dp
        imageView
    }

    val imageViewPlay by lazy {
        val imageView = BaseImageView()
        imageView.setBackgroundResource(R.drawable.ic_play)

        imageView
    }

    val imageViewSign by lazy {
        val imageView = BaseImageView()
        imageView.cornerRadius = 8.dp
        imageView.backgroundColor = Color.argb(0x40, 0, 0,0)

        imageView.initialize()
        imageView
    }

    val textViewSign by lazy {
        val textView = BaseTextView()
        textView.text = "Записаться"
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT
        textView.textSize = 11F
        textView.textColor = Color.WHITE
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }

    val textViewTitle by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textSize = 15F
        textView.textColor = Color.parseColor("#333333")
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }

    val textViewDescribe by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT
        textView.textSize = 11F
        textView.textColor = Color.parseColor("#979797")
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }

    init {

        renderUI()
    }
}

fun AnnounceViewHolder.renderUI() {
    constraintLayout.subviews(
        imageViewPhoto,
        imageViewSign,
        textViewSign,
        imageViewPlay,
        textViewTitle,
        textViewDescribe
    )

    imageViewPhoto
        .fillHorizontally(8)
        .height(utils.variable.displayWidth * .5F)
        .constrainTopToTopOf(constraintLayout, 16)

    imageViewPlay
        .width(utils.variable.displayWidth * .2F)
        .height(utils.variable.displayWidth * .2F)
        .constrainCenterYToCenterYOf(imageViewPhoto)
        .constrainCenterXToCenterXOf(imageViewPhoto)

    imageViewSign
        .width(96)
        .height(20)
        .constrainCenterXToCenterXOf(imageViewPhoto)
        .constrainBottomToBottomOf(imageViewPhoto, 16)

    textViewSign
        .constrainCenterXToCenterXOf(imageViewSign)
        .constrainCenterYToCenterYOf(imageViewSign)

    textViewTitle
        .fillHorizontally(8)
        .constrainTopToBottomOf(imageViewPhoto, 8)

    textViewDescribe
        .fillHorizontally(8)
        .constrainTopToBottomOf(textViewTitle,4)
}

fun AnnounceViewHolder.initialize() {
    addImageFromGlide(imageViewPhoto, R.drawable.image2)
    textViewTitle.text = "Тема: Высвобождение внутренней энергии"
    textViewDescribe.text = "Лектор: Горбунова Ольга Владимировна"

    val type = true

    if (type) {

    } else {

    }
}
