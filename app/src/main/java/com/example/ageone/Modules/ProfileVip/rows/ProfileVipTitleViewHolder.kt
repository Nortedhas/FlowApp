package com.example.ageone.Modules.ProfileVip.rows

import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*

class ProfileVipTitleViewHolder(val constraintLayout: ConstraintLayout): BaseViewHolder(constraintLayout) {

    val textView by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.parseColor("#979797")
        textView.textSize = 15F
        textView.gravity = Gravity.CENTER
        textView.typeface = Typeface.DEFAULT
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }
    
    init {
    
        renderUI()    
    }
}

fun ProfileVipTitleViewHolder.renderUI() {
    
    constraintLayout.subviews(
        textView
    )

    textView
        .fillHorizontally(16)
        .constrainTopToTopOf(constraintLayout, 8)
        .constrainBottomToBottomOf(constraintLayout, 8)
}

fun ProfileVipTitleViewHolder.initialize() {
    val text = "Покупая VIP-доступ, вам будет доступен весь платный контент на выбранный период"
    val declaration = "VIP-доступ"

    val spannableContent = SpannableString(text)
    spannableContent.setSpan(
        ForegroundColorSpan(Color.parseColor("#707ABA")),
        8,  8 + declaration.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)

    textView.text = spannableContent
}