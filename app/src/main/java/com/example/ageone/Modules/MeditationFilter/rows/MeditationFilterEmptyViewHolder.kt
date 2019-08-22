package com.example.ageone.Modules.MeditationFilter.rows

import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import yummypets.com.stevia.fillHorizontally
import yummypets.com.stevia.height

class MeditationFilterEmptyViewHolder(val constraintLayout: ConstraintLayout): BaseViewHolder(constraintLayout) {

    init {

        renderUI()
    }
}

fun MeditationFilterEmptyViewHolder.renderUI() {

    constraintLayout
        .fillHorizontally()
        .height(100)
}

fun MeditationFilterEmptyViewHolder.initialize() {

}