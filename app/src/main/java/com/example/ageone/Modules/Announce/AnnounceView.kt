package com.example.ageone.Modules.Announce

import android.graphics.Color
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.Application.R
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.RecyclerView.BaseAdapter
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Modules.Announce.rows.AnnounceViewHolder
import com.example.ageone.Modules.Announce.rows.initialize
import com.example.ageone.UIComponents.ViewHolders.TitleViewHolder
import com.example.ageone.UIComponents.ViewHolders.initialize
import yummypets.com.stevia.*

class AnnounceView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {
        setBackgroundResource(R.drawable.base_background)

        toolbar.title = "Анонсы"
        renderToolbar()

        bodyTable.adapter = viewAdapter
//        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER

        renderUIO()

    }
}

fun AnnounceView.renderUIO() {

    renderBodyTable()

}

class Factory(val rootModule: BaseModule) : BaseAdapter<BaseViewHolder>() {

    companion object {
        private const val AnnounceTitleType = 0
        private const val AnnounceVideoType = 1
    }

    override fun getItemCount(): Int = 10

    val titleVideoPosition = 7

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
                holder.initialize()
            }
        }
    }

}
