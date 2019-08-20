package com.example.ageone.Modules.Meditation.rows

import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.ageone.Application.R
import com.example.ageone.Application.currentActivity
import com.example.ageone.External.Base.RecyclerView.BaseRecyclerView
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.UIComponents.ViewHolders.MeditationCardViewHolder
import com.example.ageone.UIComponents.ViewHolders.SetViewHolder
import com.example.ageone.UIComponents.ViewHolders.initialize
import yummypets.com.stevia.*

class MeditationPopularViewHolder(val constraintLayout: ConstraintLayout): BaseViewHolder(constraintLayout) {

    val recyclerViewHor by lazy {
        val recyclerViewHor = BaseRecyclerView()
        recyclerViewHor
    }

    val viewAdapter by lazy {
        val viewAdapter = Factory()
        viewAdapter
    }


    init {
        recyclerViewHor.adapter = viewAdapter
        recyclerViewHor.layoutManager = LinearLayoutManager(currentActivity, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewHor.overScrollMode = View.OVER_SCROLL_NEVER

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerViewHor)

        constraintLayout.subviews(
            recyclerViewHor
        )

        recyclerViewHor
            .constrainTopToTopOf(constraintLayout, 8)
            .fillHorizontally()
    }
}

fun MeditationPopularViewHolder.initialize() {

}


class Factory: RecyclerView.Adapter<MeditationCardViewHolder>() {

    override fun getItemCount(): Int = 3

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeditationCardViewHolder {
        val layout = ConstraintLayout(parent.context)
        layout
            .width(matchParent)
            .height(wrapContent)

        return MeditationCardViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MeditationCardViewHolder, position: Int) {
        holder.initialize(223 + 20, 135, R.drawable.kitty,
            "Дыхание природы", "Задача организации, в особенности же консультация.")
    }
}