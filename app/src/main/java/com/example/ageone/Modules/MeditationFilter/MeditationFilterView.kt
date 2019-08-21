package com.example.ageone.Modules.MeditationFilter

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ageone.Application.R
import com.example.ageone.Application.currentActivity
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.RecyclerView.BaseAdapter
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Modules.MeditationFilter.rows.MeditationFilterTimeButtonViewHolder
import com.example.ageone.Modules.MeditationFilter.rows.initialize
import com.example.ageone.UIComponents.ViewHolders.TitleViewHolder
import com.example.ageone.UIComponents.ViewHolders.initialize
import yummypets.com.stevia.*

class MeditationFilterView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    val layoutManager by lazy {
        val layoutManager = GridLayoutManager(currentActivity, 6)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (position) {
                    0, 4 -> 6
                    in 1..3 -> 2
                    else -> 3
                }
            }
        }
        layoutManager
    }

    init {
        setBackgroundResource(R.drawable.back_filter)

        toolBar.title = "Подбор медитации"
        toolBar.setTitleTextColor(Color.WHITE)

        bodyTable.layoutManager = layoutManager
        bodyTable.adapter = viewAdapter
        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER

        renderUIO()

    }
}

fun MeditationFilterView.renderUIO() {

    innerContent.subviews(
        bodyTable
    )

    bodyTable
        .fillHorizontally()
        .fillVertically()
        .constrainTopToTopOf(innerContent)

}

class Factory(val rootModule: BaseModule) : BaseAdapter<BaseViewHolder>() {

    companion object {
        private const val MeditationFilterTitleType = 0
        private const val MeditationFilterTimeButtonType = 1
    }

    override fun getItemCount(): Int = 4

    override fun getItemViewType(position: Int): Int = when (position) {
        0, 4 -> MeditationFilterTitleType
        in 1..3 -> MeditationFilterTimeButtonType
        else -> -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layout = ConstraintLayout(parent.context)

        layout
            .width(matchParent)
            .height(wrapContent)

        val holder = when (viewType) {
            MeditationFilterTitleType -> {
                TitleViewHolder(layout)
            }
            MeditationFilterTimeButtonType -> {
                MeditationFilterTimeButtonViewHolder(layout)
            }
            else ->
                BaseViewHolder(layout)
        }

        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder) {
            is TitleViewHolder -> {
                val title = if (position == 0) "Выберите длительность медитации:" else "Выберите цель медитации:"
                holder.initialize(title, Color.WHITE)
            }
            is MeditationFilterTimeButtonViewHolder -> {
                holder.initialize()
            }
        }
    }

}
