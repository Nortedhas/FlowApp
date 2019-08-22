package com.example.ageone.Modules.SetsFilter.rows

import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import yummypets.com.stevia.fillHorizontally
import yummypets.com.stevia.height

class SetsFilterEmptyViewHolder(val constraintLayout: ConstraintLayout): BaseViewHolder(constraintLayout) {

    init {

        renderUI()
    }
}

fun SetsFilterEmptyViewHolder.renderUI() {

    constraintLayout
        .fillHorizontally()
        .height(100)
}

fun SetsFilterEmptyViewHolder.initialize() {

}