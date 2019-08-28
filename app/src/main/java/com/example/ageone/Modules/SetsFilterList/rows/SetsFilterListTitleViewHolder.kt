package com.example.ageone.Modules.SetsFilterList.rows

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.Application.utils
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextView.BaseTextView
import com.example.ageone.External.Base.TextView.limitLength
import com.example.ageone.External.Base.View.BaseView
import yummypets.com.stevia.*

class SetsFilterListTitleViewHolder(val constraintLayout: ConstraintLayout) : BaseViewHolder(constraintLayout) {

    val back by lazy {
        val view = BaseView()
        view.backgroundColor = Color.WHITE
        view.cornerRadius = 8.dp
        view.initialize()
        view.elevation = 4F.dp
        view
    }

    val imageViewRune by lazy {
        val imageView = BaseImageView()
        imageView.elevation = 5F.dp
        imageView
    }

    val textViewTitle by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.rgb(0x70, 0x7A,0xBA)
        textView.textSize = 15F
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.elevation = 5F.dp
        textView.text = "Вы выбрали - Уруз"
        textView
    }

    val textViewDescribe by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.parseColor("#979797")
        textView.textSize = 11F
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.elevation = 5F.dp
        textView
    }



    init {
        textViewDescribe.limitLength(90)
        renderUI()
    }
}

fun SetsFilterListTitleViewHolder.renderUI() {
    constraintLayout.subviews(
        back.subviews(
            imageViewRune,
            textViewTitle,
            textViewDescribe
        )
    )

    back
        .constrainTopToTopOf(constraintLayout, 16)
        .constrainBottomToBottomOf(constraintLayout, 8)
        .fillHorizontally(8)

    imageViewRune
        .constrainTopToTopOf(back, 16)
        .constrainLeftToLeftOf(back, 14)
        .height(71)
        .width(54)
//        .constrainBottomToBottomOf(back, 8)

    textViewTitle
        .fillHorizontally()
        .constrainTopToTopOf(back, 16)
        .constrainLeftToRightOf(imageViewRune, 10)
        .constrainRightToRightOf(back, 8)

    textViewDescribe
        .constrainTopToBottomOf(textViewTitle, 2)
        .constrainBottomToBottomOf(back, 16)
        .fillHorizontally()
        .constrainLeftToRightOf(imageViewRune, 10)
        .constrainRightToRightOf(back, 8)
}

fun SetsFilterListTitleViewHolder.initialize(rune: Int, title: String, describe: String) {
    imageViewRune.setBackgroundResource(rune)
    textViewTitle.text = title
    textViewDescribe.text = describe
}
