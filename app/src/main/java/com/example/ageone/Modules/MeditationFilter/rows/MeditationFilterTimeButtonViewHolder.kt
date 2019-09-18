package com.example.ageone.Modules.MeditationFilter.rows

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.widget.LinearLayout
import android.widget.RadioGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.Application.currentActivity
import com.example.ageone.Application.rxData
import com.example.ageone.Application.utils
import com.example.ageone.External.Base.RadioButton.BaseRadioButton
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.SCAG.Enums
import yummypets.com.stevia.*


class MeditationFilterTimeButtonViewHolder(val constraintLayout: ConstraintLayout) : BaseViewHolder(constraintLayout) {
    
    val radioGroup by lazy {
        val radioGroup = RadioGroup(currentActivity)
        radioGroup.orientation = LinearLayout.HORIZONTAL
        radioGroup
    }

    val checkBack by lazy {
        val view = GradientDrawable()
        view.setColor(Color.WHITE)
        view.cornerRadius = 8F.dp
        view
    }

    val uncheckBack by lazy {
        val view = GradientDrawable()
        view.setColor(Color.rgb(0xD5,0xC9,0xF1))
        view.cornerRadius = 8F.dp
        view
    }
    
    val radioButton1 by lazy {
        val radioButton = BaseRadioButton()
        radioButton.text = "до 7 мин."
        radioButton.setTextColor(Color.rgb(0x70,0x7A,0xBA))
        radioButton.textSize = 15F
        radioButton.setButtonBottom()
        radioButton.isChecked = true
        radioButton.background = checkBack
        radioButton
    }

    val radioButton2 by lazy {
        val radioButton = BaseRadioButton()
        radioButton.text = "7 - 25 мин."
        radioButton.setTextColor(Color.rgb(0x70,0x7A,0xBA))
        radioButton.textSize = 15F
        radioButton.setButtonBottom()
        radioButton.background = uncheckBack
        radioButton
    }

    val radioButton3 by lazy {
        val radioButton = BaseRadioButton()
        radioButton.text = "25 - 45 мин."
        radioButton.setTextColor(Color.rgb(0x70,0x7A,0xBA))
        radioButton.textSize = 15F
        radioButton.setButtonBottom()
        radioButton.background = uncheckBack
        radioButton
    }

    init {
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                radioButton1.id -> {
                    rxData.durationSearch = Enums.ProductType.lessThen7.name
                    radioButton1.background = checkBack
                    radioButton2.background = uncheckBack
                    radioButton3.background = uncheckBack
                }
                radioButton2.id -> {
                    rxData.durationSearch = Enums.ProductType.from7To25.name
                    radioButton2.background = checkBack
                    radioButton1.background = uncheckBack
                    radioButton3.background = uncheckBack
                }
                radioButton3.id -> {
                    rxData.durationSearch = Enums.ProductType.from25To45.name
                    radioButton3.background = checkBack
                    radioButton2.background = uncheckBack
                    radioButton1.background = uncheckBack
                }
            }
        }

        renderUI()
    }

}

fun MeditationFilterTimeButtonViewHolder.renderUI() {
    radioGroup.layoutParams = RadioGroup.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.WRAP_CONTENT)

    val buttonParams = RadioGroup.LayoutParams(
        utils.variable.displayWidth - 64,
        70.dp,
        1F)
    buttonParams.setMargins(8.dp, 0, 8.dp, 0)

    radioButton1.layoutParams = buttonParams
    radioButton2.layoutParams = buttonParams
    radioButton3.layoutParams = buttonParams

    constraintLayout.subviews(
        radioGroup.subviews(
            radioButton1,
            radioButton2,
            radioButton3
        )
    )

    radioGroup
        .constrainTopToTopOf(constraintLayout, 16)
        .fillHorizontally()
}

fun MeditationFilterTimeButtonViewHolder.initialize() {

}
