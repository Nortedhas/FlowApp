package com.example.ageone.Modules.MeditationFilterList

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
import com.example.ageone.Modules.Meditation.MeditationViewModel
import com.example.ageone.Modules.MeditationFilterListViewModel
import com.example.ageone.UIComponents.ViewHolders.MeditationCardViewHolder
import com.example.ageone.UIComponents.ViewHolders.initialize
import yummypets.com.stevia.*

class MeditationFilterListView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = MeditationViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    val layoutManager by lazy {
        val layoutManager = GridLayoutManager(currentActivity, 2)
        layoutManager
    }

    init {
        setBackgroundResource(R.drawable.base_background)

        toolbar.title = "Ваши медитации"
        renderToolbar()

        bodyTable.layoutManager = layoutManager
        bodyTable.adapter = viewAdapter
//        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER

        renderUIO()

    }

    inner class Factory(val rootModule: BaseModule) : BaseAdapter<BaseViewHolder>() {

        private val MeditationCardType = 0

        override fun getItemCount(): Int = 10

        override fun getItemViewType(position: Int): Int = 0

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                MeditationCardType -> {
                    MeditationCardViewHolder(layout)
                }
                else ->
                    BaseViewHolder(layout)
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
            when (holder) {
                is MeditationCardViewHolder -> {
                    holder.initialize(
                        utils.variable.displayWidth / 2 - 8, R.drawable.kitty,
                        "Спокойствие", "Медитация для тех кто проснулся и уже встал.", false)
                    holder.constraintLayout.setOnClickListener {
                        rootModule.emitEvent?.invoke(MeditationFilterListViewModel.EventType.OnMeditationPressed.toString())
                    }
                }
            }
        }

    }
}

fun MeditationFilterListView.renderUIO() {

    renderBodyTable()

}

