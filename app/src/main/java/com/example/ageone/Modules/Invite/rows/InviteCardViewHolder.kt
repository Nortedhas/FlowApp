package com.example.ageone.Modules.Invite.rows

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.Application.R
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextView.BaseTextView
import com.example.ageone.External.Base.View.BaseView
import yummypets.com.stevia.*

class InviteCardViewHolder(val constraintLayout: ConstraintLayout): BaseViewHolder(constraintLayout) {

    val image by lazy {
        val base = BaseImageView()
        base.setImageResource(R.drawable.add)
        base
    }

    val textViewTitle by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.WHITE
        textView.textSize = 21F
        textView.gravity = Gravity.CENTER
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.text = "Поделитесь счастьем с друзьями!"
        textView
    }


    val textViewDescribe by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.WHITE
        textView.textSize = 14F
        textView.gravity = Gravity.CENTER
        textView.typeface = Typeface.DEFAULT
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.text = "Пригласите друзей, и тогда вы с вашими друзьми получите по неделе VIP доступа в приложении"
        textView
    }

    init {

        renderUI()
    }

}

fun InviteCardViewHolder.renderUI() {
    constraintLayout.subviews(
        image,
        textViewTitle,
        textViewDescribe
    )

    image
        .constrainTopToTopOf(constraintLayout, 40)
        .constrainRightToRightOf(constraintLayout)
        .constrainLeftToLeftOf(constraintLayout)
        .width(122)
        .height(122)

    textViewTitle
        .fillHorizontally(60)
        .constrainTopToBottomOf(image, 40)

    textViewDescribe
        .fillHorizontally(16)
        .constrainTopToBottomOf(textViewTitle, 16)
}

fun InviteCardViewHolder.initialize() {

}