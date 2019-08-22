package com.example.ageone.Modules.MeditationFilter.rows

import android.graphics.Color
import android.graphics.Typeface
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.Application.utils
import com.example.ageone.External.Base.Button.BaseButton
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import yummypets.com.stevia.*

class MeditationFilterGoalViewHolder(val constraintLayout: ConstraintLayout): BaseViewHolder(constraintLayout) {

    val buttonGoal by lazy {
        val button = BaseButton()
        button.textSize = 15F
        button.textColor = Color.WHITE
        button.typeface = Typeface.DEFAULT
        button.backgroundColor = Color.TRANSPARENT
        button.cornerRadius = 60
        button.borderColor = Color.WHITE
        button.borderWidth = 2.dp
        button.initialize()
        button
    }

    init {
        buttonGoal.setOnClickListener {
            if (buttonGoal.borderWidth != null) {
                buttonGoal.borderWidth = null
                buttonGoal.backgroundColor = Color.WHITE
                buttonGoal.textColor = Color.rgb(0x70, 0x7A, 0xBA)
                buttonGoal.initialize()
            } else {
                buttonGoal.borderWidth = 2.dp
                buttonGoal.backgroundColor = Color.TRANSPARENT
                buttonGoal.textColor = Color.WHITE
                buttonGoal.initialize()
            }
        }
        renderUI()
    }
}

fun MeditationFilterGoalViewHolder.renderUI() {
    constraintLayout.subviews(
        buttonGoal
    )

    buttonGoal
        .constrainTopToTopOf(constraintLayout, 16)
        .constrainRightToRightOf(constraintLayout, 16)
        .width((utils.variable.displayWidth - 48) / 2)
}

fun MeditationFilterGoalViewHolder.initialize(text: String) {
    buttonGoal.text = text
}