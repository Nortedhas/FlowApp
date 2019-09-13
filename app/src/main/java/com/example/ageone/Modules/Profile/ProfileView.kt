package com.example.ageone.Modules.Profile

import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.Application.R
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.RecyclerView.BaseAdapter
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Models.User.user
import com.example.ageone.Modules.Profile.rows.*
import com.example.ageone.Modules.ProfileViewModel
import yummypets.com.stevia.height
import yummypets.com.stevia.matchParent
import yummypets.com.stevia.width
import yummypets.com.stevia.wrapContent

class ProfileView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = ProfileViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {
        setBackgroundResource(R.drawable.base_background)

        toolbar.title = "Профиль"
        renderToolbar()

        bodyTable.adapter = viewAdapter
        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER

        renderUIO()

    }
}

fun ProfileView.renderUIO() {

    renderBodyTable()

}

class Factory(val rootModule: BaseModule) : BaseAdapter<BaseViewHolder>() {

    companion object {
        private const val ProfileInfoType = 0
        private const val ProfileVIPType = 1
        private const val ProfileTitleType = 2
        private const val ProfileChakrasType = 3
        private const val ProfileLegendType = 4
    }

    override fun getItemCount(): Int = 5

    override fun getItemViewType(position: Int): Int = when (position) {
        0 -> ProfileInfoType
        1 -> ProfileVIPType
        2 -> ProfileTitleType
        3 -> ProfileChakrasType
        4 -> ProfileLegendType
        else -> -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layout = ConstraintLayout(parent.context)

        layout
            .width(matchParent)
            .height(wrapContent)

        val holder = when (viewType) {
            ProfileInfoType -> {
                ProfileInfoViewHolder(layout)
            }
            ProfileVIPType -> {
                GetVIPViewHolder(layout)
            }
            ProfileTitleType -> {
                ProfileTitleViewHolder(layout)
            }

            ProfileChakrasType -> {
                ProfileChakrasViewHolder(layout)
            }
            ProfileLegendType -> {
                ProfileLegendViewHolder(layout)
            }
            else ->
                BaseViewHolder(layout)
        }

        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder) {
            is ProfileInfoViewHolder -> {
                holder.initialize(user.data.name, "VIP доступ до 31.10.2019")
            }
            is GetVIPViewHolder -> {
                holder.initialize("Получить VIP доступ ко всему платному контенту")
                holder.constraintLayout.setOnClickListener {
                    rootModule.emitEvent?.invoke(ProfileViewModel.EventType.OnGetVipPressed.toString())
                }
            }
            is ProfileTitleViewHolder -> {
                holder.initialize("Ваши успехи", "Выполняйте различные медитации, заполняйте ваши чакры")
            }
            is ProfileChakrasViewHolder -> {
                holder.initialize()
            }
            is ProfileLegendViewHolder -> {
                holder.initialize()
            }
        }
    }

}
