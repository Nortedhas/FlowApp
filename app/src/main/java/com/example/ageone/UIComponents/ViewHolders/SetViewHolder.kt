package com.example.ageone.UIComponents.ViewHolders

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.Application.R
import com.example.ageone.Application.currentActivity
import com.example.ageone.Application.utils
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextView.BaseTextView
import com.example.ageone.External.Libraries.Glide.addImageFromGlide
import eightbitlab.com.blurview.BlurView
import yummypets.com.stevia.*
import eightbitlab.com.blurview.RenderScriptBlur

class SetViewHolder(val constraintLayout: ConstraintLayout): BaseViewHolder(constraintLayout) {

    val imageViewPhoto by lazy {
        val imageView = BaseImageView()
        imageView.cornerRadius = 8.dp
        imageView.backgroundColor = Color.parseColor("#EBEBF0")
        imageView.initialize()
        imageView
    }

    val imageLock by lazy {
        val image = BaseImageView()
        image.setBackgroundResource(R.drawable.lock_icon)
        image
    }

    val imageViewCount by lazy {
        val imageView = BaseImageView()
        imageView.cornerRadius = 8.dp
        imageView.backgroundColor = Color.argb(0x60, 0, 0,0с

        imageView.initialize()
        imageView
    }

    val textViewCount by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT
        textView.textSize = 11F
        textView.textColor = Color.WHITE
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }

    val imageViewBottom by lazy {
        val imageView = BaseImageView()
        imageView.setBackgroundResource(R.drawable.sets_bottom)
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

   /* val blurView by lazy {
        val blurView = BlurView(currentActivity)
        blurView
    }*/
    
/*    val blurView by lazy {
        val blurView = BlurringView(currentActivity)
        blurView.setBlurRadius(4)
        blurView.setDownsampleFactor(6)
        blurView.setOverlayColor(Color.argb(0x60, 0, 0,0))
        blurView
    }*/

    init {
        constraintLayout.subviews(
            imageViewPhoto,
            imageLock,
            /*blurView.subviews(
                imageViewCount
            ),*/
            imageViewCount,
            textViewCount,
            imageViewBottom,
            textViewTitle,
            textViewDescribe
        )

        imageViewPhoto
            .constrainTopToTopOf(constraintLayout, 16)
            .fillHorizontally(8)

        imageLock
            .constrainRightToRightOf(imageViewPhoto, 8)
            .constrainTopToTopOf(imageViewPhoto, 8)
            .width(23)
            .height(23)

        /*blurView
            .constrainRightToRightOf(imageViewPhoto)
            .constrainLeftToLeftOf(imageViewPhoto)
            .constrainBottomToBottomOf(imageViewPhoto, 8)
            .width(86)
            .height(20)*/

        val radius = 20f
        val windowBackground = currentActivity?.window?.decorView?.background

        /*blurView.setupWith(constraintLayout)
            .setFrameClearDrawable(windowBackground)
            .setBlurAlgorithm(RenderScriptBlur(currentActivity))
            .setBlurRadius(radius)*/

        imageViewCount
            .constrainRightToRightOf(imageViewPhoto)
            .constrainLeftToLeftOf(imageViewPhoto)
            .constrainBottomToBottomOf(imageViewPhoto, 8)
            .width(86)
            .height(20)

        /*blurView
            .constrainBottomToBottomOf(imageViewPhoto, 8)
            .constrainLeftToLeftOf(imageViewPhoto)
            .constrainRightToRightOf(imageViewPhoto)
            .width(86)
            .height(20)

        blurView.setBlurredView(imageViewPhoto)

        blurView.invalidate()*/

        textViewCount
            .constrainRightToRightOf(imageViewCount)
            .constrainLeftToLeftOf(imageViewCount)
            .constrainTopToTopOf(imageViewCount)
            .constrainBottomToBottomOf(imageViewCount)

        imageViewBottom
            .constrainTopToBottomOf(imageViewPhoto)

        textViewTitle
            .constrainTopToBottomOf(imageViewBottom, 8)
            .fillHorizontally(8)

        textViewDescribe
            .constrainTopToBottomOf(textViewTitle, 4)
            .fillHorizontally(8)
    }
}

fun SetViewHolder.initialize(width: Int, image: Int, title: String, describe: String, count: Int) {
    constraintLayout
        .width(width)

    imageViewPhoto
        .width(width - 16)
        .height(utils.variable.displayWidth * .506F)

    addImageFromGlide(imageViewPhoto, R.drawable.kitty)

    imageViewBottom
        .width(width - 24)
        .centerHorizontally()

    textViewTitle.text = title
    textViewDescribe.text = describe
    textViewCount.text = "медитаций: $count"
}