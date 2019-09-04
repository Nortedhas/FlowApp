package com.example.ageone.UIComponents.ViewHolders

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
import java.text.FieldPosition


class MeditationCardViewHolder(val constraintLayout: ConstraintLayout): BaseViewHolder(constraintLayout) {

    val imageViewPhoto by lazy {
        val imageView = BaseImageView()
        imageView.cornerRadius = 8.dp
        imageView.backgroundColor = Color.parseColor("#EBEBF0")
        imageView.initialize()
        imageView.elevation = 4F.dp
        imageView
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
fun MeditationCardViewHolder.renderUI() {
    constraintLayout.subviews(
        imageViewPhoto,
        textViewTitle,
        textViewDescribe
    )

    imageViewPhoto
        .constrainTopToTopOf(constraintLayout, 16)
        .fillHorizontally(8)

    textViewTitle
        .constrainTopToBottomOf(imageViewPhoto, 8)
        .fillHorizontally(8)

    textViewDescribe
        .constrainTopToBottomOf(textViewTitle, 4)
        .fillHorizontally(8)
}

fun MeditationCardViewHolder.initialize(width: Int, height: Int, image: Int, title: String, describe: String) {

    constraintLayout
        .width(width)

    imageViewPhoto
        .width(width - 20)
        .height(height)

    addImageFromGlide(imageViewPhoto, R.drawable.image3)

    textViewTitle.text = title
    textViewDescribe.text = describe
}

fun MeditationCardViewHolder.initialize(width: Int, image: Int, title: String, describe: String) {

    addImageFromGlide(imageViewPhoto, R.drawable.image1)

    imageViewPhoto
        .width((utils.variable.displayWidth - 48) / 2 )
        .height(utils.variable.displayWidth * .28F)

    textViewTitle.text = title
    textViewDescribe.text = describe
}

fun MeditationCardViewHolder.initialize(width: Int, image: Int, title: String, describe: String, position: Int) {

    addImageFromGlide(imageViewPhoto, R.drawable.image1)

    imageViewPhoto
        .width((utils.variable.displayWidth - 48) / 2 )
        .height(utils.variable.displayWidth * .28F)

    textViewTitle.text = title
    textViewDescribe.text = describe

    if (position % 2 == 0) {
        imageViewPhoto
            .constrainLeftToLeftOf(constraintLayout, 16)
            .constrainRightToRightOf(constraintLayout, 8)
        textViewDescribe
            .constrainLeftToLeftOf(constraintLayout, 16)
            .constrainRightToRightOf(constraintLayout, 8)
        textViewTitle
            .constrainLeftToLeftOf(constraintLayout, 16)
            .constrainRightToRightOf(constraintLayout, 8)
    } else {
        imageViewPhoto
            .constrainLeftToLeftOf(constraintLayout, 8)
            .constrainRightToRightOf(constraintLayout, 16)
        textViewDescribe
            .constrainLeftToLeftOf(constraintLayout, 8)
            .constrainRightToRightOf(constraintLayout, 16)
        textViewTitle
            .constrainLeftToLeftOf(constraintLayout, 8)
            .constrainRightToRightOf(constraintLayout, 16)
    }
}