package com.example.ageone.Modules.Pleer

import android.R.attr.*
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import com.example.ageone.Application.R
import com.example.ageone.Application.utils
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.SeekBar.BaseSeekBar
import com.example.ageone.External.Base.TextView.BaseTextView
import com.example.ageone.External.InitModuleUI
import yummypets.com.stevia.*


class PleerView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val textView by lazy {
        val textView = BaseTextView()
        textView.text = "Включить звук на фон"
        textView.textColor = Color.WHITE
        textView.textSize = 17F
        textView.gravity = Gravity.CENTER
        textView.typeface = Typeface.DEFAULT
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }

    val imageView1 by lazy {
        val imageView = BaseImageView()
        imageView.cornerRadius = (utils.variable.displayWidth * 46 / 375).dp
        imageView.setImageResource(R.drawable.pic_music1)
        imageView.setPadding(2.dp,2.dp,2.dp,2.dp)
        imageView.initialize()
        imageView
    }

    val textViewNamed1 by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.WHITE
        textView.textSize = 11F
        textView.gravity = Gravity.CENTER
        textView.typeface = Typeface.DEFAULT
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.text = "Капли дождя"
        textView
    }

    val imageView2 by lazy {
        val imageView = BaseImageView()
        imageView.cornerRadius = utils.variable.displayWidth * 46 / 375.dp
        imageView.setImageResource(R.drawable.pic_music2)
        imageView.setPadding(2.dp,2.dp,2.dp,2.dp)
        imageView.initialize()
        imageView
    }

    val textViewNamed2 by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.WHITE
        textView.textSize = 11F
        textView.gravity = Gravity.CENTER
        textView.typeface = Typeface.DEFAULT
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.text = "Шум ветра"
        textView
    }

    val imageView3 by lazy {
        val imageView = BaseImageView()
        imageView.cornerRadius = utils.variable.displayWidth * 46 / 375.dp
        imageView.setImageResource(R.drawable.pic_music3)
        imageView.setPadding(2.dp,2.dp,2.dp,2.dp)
        imageView.initialize()
        imageView
    }

    val textViewNamed3 by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.WHITE
        textView.textSize = 11F
        textView.gravity = Gravity.CENTER
        textView.typeface = Typeface.DEFAULT
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.text = "Треск камина"
        textView
    }

    val imageView4 by lazy {
        val imageView = BaseImageView()
        imageView.cornerRadius = utils.variable.displayWidth * 46 / 375.dp
        imageView.setImageResource(R.drawable.pic_music4)
        imageView.setPadding(2.dp,2.dp,2.dp,2.dp)
        imageView.initialize()
        imageView
    }

    val textViewNamed4 by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.WHITE
        textView.textSize = 11F
        textView.gravity = Gravity.CENTER
        textView.typeface = Typeface.DEFAULT
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.text = "Под водой"
        textView
    }

    val textViewSound by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.WHITE
        textView.textSize = 13F
        textView.gravity = Gravity.CENTER
        textView.typeface = Typeface.DEFAULT
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.text = "Громкость фонового звука"
        textView
    }

    val textViewName by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.WHITE
        textView.textSize = 27F
        textView.gravity = Gravity.CENTER
        textView.typeface = Typeface.DEFAULT
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.text = "Дыхание природы"
        textView
    }

    val textViewDescribe by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.WHITE
        textView.textSize = 17F
        textView.gravity = Gravity.CENTER
        textView.typeface = Typeface.DEFAULT
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.text = "Не следует, однако забывать, что рамки и место обучения."
        textView
    }

    val seekBarSound by lazy {
        val view = BaseSeekBar()
        view.setBackgroundColor(Color.TRANSPARENT)
        view.colorThumb = Color.WHITE
        view.colorProgressLine = Color.WHITE
        view.initialize()
        view
    }

    val seekBarMeditation by lazy {
        val view = BaseSeekBar()
        view.setBackgroundColor(Color.TRANSPARENT)
        view.colorThumb = Color.WHITE
        view.colorProgressLine = Color.WHITE
        view.initialize()
        view
    }

    var isPlay = true
    val viewButton by lazy {
        val view = BaseImageView()
        view.setBackgroundResource(R.drawable.button_play)
        view
    }

    val viewExit by lazy {
        val view = BaseImageView()
        view.setBackgroundResource(R.drawable.ic_close)
        view
    }

    init {
        setBackgroundResource(R.drawable.back_pleer)

        toolbar.title = "Прослушивание"
        renderToolbar()

        var isSelected = true

        imageView1.setOnClickListener {
            OnClickImage(0)
        }
        imageView2.setOnClickListener {
            OnClickImage(1)
        }
        imageView3.setOnClickListener {
            OnClickImage(2)
        }
        imageView4.setOnClickListener {
            OnClickImage(3)
        }

        viewButton.setOnClickListener {
            viewButton.setBackgroundResource(
                if (isPlay) R.drawable.button_stop else R.drawable.button_play
            )
            isPlay = !isPlay
        }


        renderUIO()

    }

    fun OnClickImage(selected: Int) {
        val imageViews = arrayOf(imageView1, imageView2, imageView3, imageView4)

        val selectedColor = Color.parseColor("#8863E6")

        val gD = GradientDrawable()
        gD.setColor(selectedColor)
        gD.shape = GradientDrawable.OVAL

        for (i in imageViews.indices) {
            if (selected == i) {
                imageViews[i].background = gD
            } else {
                imageViews[i].background = null
            }
        }

    }
}

fun PleerView.renderUIO() {

    innerContent.subviews(
        textView,
        imageView1,
        textViewNamed1,
        imageView2,
        textViewNamed2,
        imageView3,
        textViewNamed3,
        imageView4,
        textViewNamed4,
        textViewSound,
        seekBarSound,
        textViewName,
        textViewDescribe,
        seekBarMeditation,
        viewButton
    )

    textView
        .fillHorizontally()
        .constrainTopToTopOf(innerContent, utils.variable.displayWidth * 8 / 375)

    val size = utils.variable.displayWidth * 46 / 375
    val topMarginImage = utils.variable.displayWidth * 24 / 375
    val spaceMargin = utils.variable.displayWidth * 38 / 375
    val topMarginText = utils.variable.displayWidth * 8 / 375

    imageView1
        .width(size)
        .height(size)
        .constrainTopToBottomOf(textView, topMarginImage)
        .constrainLeftToLeftOf(innerContent, spaceMargin)

    textViewNamed1
        .width(size)
        .constrainTopToBottomOf(imageView1, topMarginText)
        .constrainLeftToLeftOf(imageView1)

    imageView2
        .width(size)
        .height(size)
        .constrainTopToBottomOf(textView, topMarginImage)
        .constrainLeftToRightOf(imageView1, spaceMargin)

    textViewNamed2
        .width(size)
        .constrainTopToBottomOf(imageView2, topMarginText)
        .constrainLeftToLeftOf(imageView2)

    imageView3
        .width(size)
        .height(size)
        .constrainTopToBottomOf(textView, topMarginImage)
        .constrainLeftToRightOf(imageView2, spaceMargin)

    textViewNamed3
        .width(size)
        .constrainTopToBottomOf(imageView3, topMarginText)
        .constrainLeftToLeftOf(imageView3)

    imageView4
        .width(size)
        .height(size)
        .constrainTopToBottomOf(textView, topMarginImage)
        .constrainLeftToRightOf(imageView3, spaceMargin)

    textViewNamed4
        .width(size)
        .constrainTopToBottomOf(imageView4, topMarginText)
        .constrainLeftToLeftOf(imageView4)

    textViewSound
        .fillHorizontally()
        .constrainTopToBottomOf(textViewNamed1, utils.variable.displayWidth * 20 / 375)

    seekBarSound
        .width(utils.variable.displayWidth * 257 / 375)
        .fillHorizontally(utils.variable.displayWidth * 55 / 375)
        .constrainTopToBottomOf(textViewSound, utils.variable.displayWidth * 15 / 375)

    textViewName
        .fillHorizontally(8)
        .constrainBottomToTopOf(textViewDescribe, utils.variable.displayWidth * 8 / 375)

    textViewDescribe
        .fillHorizontally(utils.variable.displayWidth * 50 / 375)
        .constrainBottomToTopOf(seekBarMeditation, utils.variable.displayWidth * 15 / 375)

    seekBarMeditation
        .fillHorizontally(8)
        .constrainBottomToTopOf(viewButton, utils.variable.displayWidth * 45 / 375)

    val sizeButton = utils.variable.displayWidth * 85 / 375
    viewButton
        .width(sizeButton)
        .height(sizeButton)
        .constrainRightToRightOf(innerContent)
        .constrainLeftToLeftOf(innerContent)
        .constrainBottomToBottomOf(innerContent, utils.variable.displayWidth * 45 / 375)
}