package com.example.ageone.Modules.Profile.rows

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.Application.R
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*

class ProfileInfoViewHolder(val constraintLayout: ConstraintLayout) : BaseViewHolder(constraintLayout) {

    val imageView by lazy {
        val imageView = BaseImageView()
        imageView.setBackgroundResource(R.drawable.ic_profile)
        imageView
    }

    val textViewTitle by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textSize = 15F
        textView.textColor = Color.rgb(0x70,0x7A,0xBA)
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }

    val textViewDescribe by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT
        textView.textSize = 15F
        textView.textColor = Color.parseColor("#333333")
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }

    init {

        renderUI()
    }
}

fun ProfileInfoViewHolder.renderUI() {
    constraintLayout.subviews(
        imageView,
        textViewTitle,
        textViewDescribe
    )

    imageView
        .height(60)
        .width(60)
        .constrainTopToTopOf(constraintLayout, 16)
        .constrainLeftToLeftOf(constraintLayout, 8)

    textViewTitle
        .fillHorizontally()
        .constrainTopToTopOf(constraintLayout, 24)
        .constrainLeftToRightOf(imageView, 16)

    textViewDescribe
        .fillHorizontally()
        .constrainTopToBottomOf(textViewTitle, 4)
        .constrainLeftToRightOf(imageView, 16)
}

fun ProfileInfoViewHolder.initialize(title: String, describe: String) {
    textViewTitle.text = title
    textViewDescribe.text = describe
}
