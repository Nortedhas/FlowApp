package com.example.ageone.Modules.SetsFilterList

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ageone.Application.R
import com.example.ageone.Application.currentActivity
import com.example.ageone.Application.utils
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.RecyclerView.BaseAdapter
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Modules.SetsFilterList.rows.SetsFilterListTitleViewHolder
import com.example.ageone.Modules.SetsFilterList.rows.initialize
import com.example.ageone.UIComponents.ViewHolders.SetViewHolder
import com.example.ageone.UIComponents.ViewHolders.initialize
import yummypets.com.stevia.*

class SetsFilterListView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    val layoutManager by lazy {
            val layoutManager = GridLayoutManager(currentActivity, 2)
            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (position) {
                        0 -> 2
                        else -> 1
                    }
                }
            }
            layoutManager
        }

    init {
        setBackgroundResource(R.drawable.base_background)

        toolBar.title = "Ваши сеты"
        toolBar.setTitleTextColor(Color.WHITE)

        bodyTable.layoutManager = layoutManager
        bodyTable.adapter = viewAdapter
        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER

        renderUIO()

    }
}

fun SetsFilterListView.renderUIO() {

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
        private const val SetsFilterListTitleType = 0
        private const val SetsFilterListSetType = 1
    }

    override fun getItemCount(): Int = 5

    override fun getItemViewType(position: Int): Int = when (position) {
        0 -> SetsFilterListTitleType
        else -> SetsFilterListSetType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layout = ConstraintLayout(parent.context)

        layout
            .width(matchParent)
            .height(wrapContent)

        val holder = when (viewType) {
            SetsFilterListTitleType -> {
                SetsFilterListTitleViewHolder(layout)
            }
            SetsFilterListSetType -> {
                SetViewHolder(layout)
            }
            else ->
                BaseViewHolder(layout)
        }

        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder) {
            is SetsFilterListTitleViewHolder -> {
                holder.initialize(R.drawable.ic_rune2, "Вы выбрали - Уруз",
                    "Руна Уруз символ того ,что все рано или поздно приходит к завершению, " +
                            "а что-то начинается. Толкование значения руны Уруз недвусмысленно: " +
                            "конец всегда есть началом нового. ")
            }
            is SetViewHolder -> {
                holder.initialize(
                    utils.variable.displayWidth / 2 - 8, R.drawable.kitty,
                    "Спокойствие", "Медитация для тех кто проснулся и уже встал.", position)
            }
        }
    }

}
