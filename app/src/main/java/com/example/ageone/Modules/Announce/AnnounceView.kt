package com.example.ageone.Modules.Announce

import android.graphics.Color
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.Application.R
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.RecyclerView.BaseAdapter
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.InitModuleUI
import com.example.ageone.External.RxBus.RxBus
import com.example.ageone.External.RxBus.RxEvent
import com.example.ageone.Modules.Announce.rows.AnnounceViewHolder
import com.example.ageone.Modules.Announce.rows.initialize
import com.example.ageone.UIComponents.ViewHolders.TitleViewHolder
import com.example.ageone.UIComponents.ViewHolders.initialize
import timber.log.Timber
import yummypets.com.stevia.*

class AnnounceView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = AnnounceViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {
        viewModel.loadRealmData()
        setBackgroundResource(R.drawable.base_background)

        toolbar.title = "Анонсы"
        renderToolbar()

        bodyTable.adapter = viewAdapter
//        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER

        renderUIO()
        bindUI()
    }

    fun bindUI() {
        compositeDisposable.add(
            RxBus.listen(RxEvent.EventReloadAnnounce::class.java).subscribe {
                bodyTable.adapter?.notifyDataSetChanged()
            }
        )
    }

    inner class Factory(val rootModule: BaseModule) : BaseAdapter<BaseViewHolder>() {

        private val AnnounceTitleType = 0
        private val AnnounceVideoType = 1

        override fun getItemCount(): Int = viewModel.realmData.size + 2

        val titleVideoPosition = viewModel.model.countVideos + 1

        override fun getItemViewType(position: Int): Int = when (position) {
            0, titleVideoPosition -> AnnounceTitleType
            else -> AnnounceVideoType
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                AnnounceTitleType -> {
                    TitleViewHolder(layout)
                }
                AnnounceVideoType -> {
                    AnnounceViewHolder(layout)
                }
                else ->
                    BaseViewHolder(layout)
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
            when (holder) {
                is TitleViewHolder -> {
                    val text = if (position == 0) "Рекомендуемые лекции" else "Предстоящие мероприятия"
                    holder.initialize(text, Color.rgb(0x70,0x07A,0xBA))
                }
                is AnnounceViewHolder -> {
                    val index = position - if (position > viewModel.model.countVideos + 1) 2 else 1
                    Timber.i("Current index: $index")
                    val announce = viewModel.realmData[index]
                    holder.initialize(announce)
                }
            }
        }
    }
}

fun AnnounceView.renderUIO() {

    renderBodyTable()

}


