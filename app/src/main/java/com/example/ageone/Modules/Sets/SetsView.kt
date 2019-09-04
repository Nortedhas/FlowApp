package com.example.ageone.Modules.Sets

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
import com.example.ageone.Modules.Sets.rows.SetsTestButtonViewHolder
import com.example.ageone.Modules.Sets.rows.initialize
import com.example.ageone.UIComponents.ViewHolders.SetViewHolder
import com.example.ageone.UIComponents.ViewHolders.initialize
import yummypets.com.stevia.*

class SetsView(initModuleUI: InitModuleUI = InitModuleUI()): BaseModule(initModuleUI) {
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

        toolbar.title = "Сеты медитаций"
        renderToolbar()

        bodyTable.layoutManager = layoutManager
        bodyTable.adapter = viewAdapter
//        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER

        renderUIO()
    }

}

fun SetsView.renderUIO() {

    renderBodyTable()
}

class Factory(val rootModule: BaseModule): BaseAdapter<BaseViewHolder>() {

    companion object {
        private const val SetsTestButtonType = 0
        private const val SetsCardType = 1
    }

    override fun getItemCount(): Int = 10

    override fun getItemViewType(position: Int):Int = when(position) {
        0 -> SetsTestButtonType
        else -> SetsCardType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layout = ConstraintLayout(parent.context)

        layout
            .width(matchParent)
            .height(wrapContent)

        val holder = when(viewType) {
            SetsTestButtonType ->
                SetsTestButtonViewHolder(layout)
            SetsCardType ->
                SetViewHolder(layout)
            else ->
                BaseViewHolder(layout)
        }

        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when(holder) {
            is SetsTestButtonViewHolder -> {
                holder.initialize(R.drawable.button_test)
                holder.imageViewSearch.setOnClickListener {
                    rootModule.emitEvent?.invoke(SetsViewModel.EventType.OnTestPressed.toString())
                }
            }

            is SetViewHolder -> {
                holder.initialize(
                    utils.variable.displayWidth / 2 - 8, R.drawable.kitty,
                    "Спокойствие", "Медитация для тех кто проснулся и уже встал.", position, position)
                holder.constraintLayout.setOnClickListener {
                    rootModule.emitEvent?.invoke(SetsViewModel.EventType.OnSetPressed.toString())
                }
            }
        }
    }
}