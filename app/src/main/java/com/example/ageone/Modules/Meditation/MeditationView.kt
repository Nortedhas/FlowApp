package com.example.ageone.Modules.Meditation

import android.graphics.Color
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ageone.Application.R
import com.example.ageone.Application.currentActivity
import com.example.ageone.Application.utils
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.RecyclerView.BaseAdapter
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Models.KartDao
import com.example.ageone.Models.Meditation
import com.example.ageone.Models.addMeditation
import com.example.ageone.Models.getAllMeditation
import com.example.ageone.Modules.Meditation.rows.MeditationPopularViewHolder
import com.example.ageone.Modules.Meditation.rows.MeditationSearchViewHolder
import com.example.ageone.Modules.Meditation.rows.initialize
import com.example.ageone.UIComponents.ViewHolders.TitleViewHolder
import com.example.ageone.UIComponents.ViewHolders.initialize
import com.example.ageone.UIComponents.ViewHolders.MeditationCardViewHolder
import io.realm.Realm
import timber.log.Timber
import yummypets.com.stevia.*

class MeditationView(initModuleUI: InitModuleUI = InitModuleUI()): BaseModule(initModuleUI) {

    val viewModel = MeditationViewModel()

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

        toolbar.title = "Здравствуйте!"

        renderToolbar()

        bodyTable.layoutManager = layoutManager
        bodyTable.adapter = viewAdapter
//        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER

        renderUIO()

    }

    inner class Factory(val rootModule: BaseModule): BaseAdapter<BaseViewHolder>() {

        private val MeditationSearchType = 0
        private val MeditationTitleType = 1
        private val MeditationPopularType = 2
        private val MeditationCardType = 3


        override fun getItemCount() = viewModel.realmData.size + 4

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
                    TitleViewHolder(layout)
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
                    holder.imageViewSearch.setOnClickListener {
                        rootModule.emitEvent?.invoke(MeditationViewModel.EventType.OnSearchPressed.toString())
                    }
                }

                is TitleViewHolder -> {
                    val title = if (position == 1) "Популярные медитации" else "Быстрый старт"
                    holder.initialize(title, Color.parseColor("#707ABA"))
                    holder.textViewTitle
                        .fillHorizontally(16)
                }

                is MeditationCardViewHolder -> {
                    val meditation = viewModel.realmData[position - 4]
                    Timber.i("$meditation")
                    holder.initialize(
                        utils.variable.displayWidth / 2 - 8, R.drawable.kitty,
                        "Спокойствие", "Медитация для тех кто проснулся и уже встал.", position)

                    holder.constraintLayout.setOnClickListener {
                        rootModule.emitEvent?.invoke(MeditationViewModel.EventType.OnMeditationPressed.toString())
                    }


                }

                is MeditationPopularViewHolder -> {
                    holder.initialize()
                    holder.onTap = { position ->
                        Timber.i("Pos: $position")
                        rootModule.emitEvent?.invoke(MeditationViewModel.EventType.OnMeditationPressed.toString())
                    }
                }

            }

        }

    }

}

fun MeditationView.renderUIO() {

    renderBodyTable()
    bodyTable
        .fillHorizontally(0)
}

