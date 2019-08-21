package com.example.ageone.Modules.Meditation

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ageone.Application.R
import com.example.ageone.Application.currentActivity
import com.example.ageone.Application.utils
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.RecyclerView.BaseAdapter
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.InitModuleUI

import com.example.ageone.Modules.Meditation.rows.MeditationPopularViewHolder
import com.example.ageone.Modules.Meditation.rows.MeditationSearchViewHolder
import com.example.ageone.Modules.Meditation.rows.MeditationTitleViewHolder
import com.example.ageone.Modules.Meditation.rows.initialize
import com.example.ageone.UIComponents.ViewHolders.MeditationCardViewHolder
import com.example.ageone.UIComponents.ViewHolders.initialize
import yummypets.com.stevia.*

class MeditationView(initModuleUI: InitModuleUI = InitModuleUI()): BaseModule(initModuleUI) {

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    val layoutManager by lazy {
        val layoutManager = GridLayoutManager(currentActivity, 2)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (position) {
                    in (0..3) -> 2
                    else -> 1
                }
            }
        }
        layoutManager
    }

    init {
        setBackgroundResource(R.drawable.base_background)

        toolBar.title = "Здравствуйте!"
        toolBar.setTitleTextColor(Color.WHITE)

        bodyTable.layoutManager = layoutManager
        bodyTable.adapter = viewAdapter
        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER

        renderUIO()

    }

}

fun MeditationView.renderUIO() {
    innerContent.subviews(
        bodyTable
    )

    bodyTable
        .fillHorizontally()
        .fillVertically()
        .constrainTopToTopOf(innerContent)
}

class Factory(val rootModule: BaseModule): BaseAdapter<BaseViewHolder>() {

    companion object {
        private const val MeditationSearchType = 0
        private const val MeditationTitleType = 1
        private const val MeditationPopularType = 2
        private const val MeditationCardType = 3
    }

    override fun getItemCount() = 10

    override fun getItemViewType(position: Int):Int = when(position) {
        0 -> MeditationSearchType
        1, 3 -> MeditationTitleType
        2 -> MeditationPopularType
        else -> MeditationCardType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        val layout = ConstraintLayout(parent.context)

        layout
            .width(matchParent)
            .height(wrapContent)

        val holder = when(viewType) {
            MeditationSearchType -> {
                MeditationSearchViewHolder(layout)
            }
            MeditationTitleType -> {
                MeditationTitleViewHolder(layout)
            }
            MeditationPopularType -> {
                MeditationPopularViewHolder(layout)
            }
            MeditationCardType -> {
                MeditationCardViewHolder(layout)
            }
            else -> {
                BaseViewHolder(layout)
            }
        }

        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

        when(holder) {
            is MeditationSearchViewHolder -> {
                holder.initialize(R.drawable.button_meditation_search)
            }

            is MeditationTitleViewHolder -> {
                val title = if (position == 1) "Популярные медитации" else "Быстрый старт"
                holder.initialize(title)
            }

            is MeditationCardViewHolder -> {
                holder.initialize(
                    utils.variable.displayWidth / 2 - 8, R.drawable.kitty,
                    "Спокойствие", "Медитация для тех кто проснулся и уже встал.")
            }

            is MeditationPopularViewHolder -> {
                holder.initialize()
            }

        }

    }

}
