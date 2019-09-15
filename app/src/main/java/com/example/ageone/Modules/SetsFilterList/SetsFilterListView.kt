package com.example.ageone.Modules.SetsFilterList

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
import com.example.ageone.Modules.SetsFilterListViewModel
import com.example.ageone.UIComponents.ViewHolders.SetViewHolder
import com.example.ageone.UIComponents.ViewHolders.initialize
import yummypets.com.stevia.*

class SetsFilterListView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = SetsFilterListViewModel()

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

        toolbar.title = "Ваши сеты"
        renderToolbar()

        bodyTable.layoutManager = layoutManager
        bodyTable.adapter = viewAdapter
//        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER

        renderUIO()

    }
}

fun SetsFilterListView.renderUIO() {

    renderBodyTable()

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
                holder.initialize(R.drawable.rune2, "Вы выбрали - Уруз",
                    "ЖЖЖ ЖЖЖЖ ЖЖЖЖЖ ЖЖЖ ЖЖЖЖЖ ЖЖЖЖ ЖЖЖЖЖЖЖ" +
                            "ЖЖЖЖ ЖЖЖЖ ЖЖЖЖЖЖ ЖЖЖЖ ЖЖЖЖЖЖЖЖ ЖЖЖЖЖЖ" +
                            "ЖЖЖ ЖЖЖЖ ЖЖЖЖЖ ЖЖЖЖЖЖЖ ЖЖЖЖЖ ЖЖЖЖЖ")
            }
            is SetViewHolder -> {
                holder.initialize(
                    utils.variable.displayWidth / 2 - 8, R.drawable.kitty,
                    "Спокойствие", "Медитация для тех кто проснулся и уже встал.", position, position)
                holder.constraintLayout.setOnClickListener {
                    rootModule.emitEvent?.invoke(SetsFilterListViewModel.EventType.OnSetPressed.toString())
                }
            }
        }
    }

}
