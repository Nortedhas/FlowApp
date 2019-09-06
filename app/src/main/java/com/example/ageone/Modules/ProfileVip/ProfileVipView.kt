package com.example.ageone.Modules.ProfileVip

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
import com.example.ageone.Modules.ProfileVip.rows.ProfileVip1yearViewHolder
import com.example.ageone.Modules.ProfileVip.rows.ProfileVipCardViewHolder
import com.example.ageone.Modules.ProfileVip.rows.ProfileVipTitleViewHolder
import com.example.ageone.Modules.ProfileVip.rows.initialize
import com.example.ageone.Modules.ProfileVipViewModel
import yummypets.com.stevia.*

class ProfileVipView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = ProfileVipViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    val layoutManager by lazy {
            val layoutManager = GridLayoutManager(currentActivity, 2)
            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (position) {
                        in 0..1 -> 1
                        else -> 2
                    }
                }
            }
            layoutManager
        }

    init {
        setBackgroundResource(R.drawable.base_background)

        toolbar.title = "VIP доступ"
        renderToolbar()

        bodyTable.layoutManager = layoutManager
        bodyTable.adapter = viewAdapter
        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER

        renderUIO()

    }
}

fun ProfileVipView.renderUIO() {

    renderBodyTable()

}

class Factory(val rootModule: BaseModule) : BaseAdapter<BaseViewHolder>() {

    companion object {
        private const val ProfileVipCardType = 0
        private const val ProfileVipTitleType = 1
        private const val ProfileVipTitle1YearType = 2
    }

    override fun getItemCount(): Int = 4

    override fun getItemViewType(position: Int): Int = when (position) {
        0, 1 -> ProfileVipCardType
        2 -> ProfileVipTitle1YearType
        3 -> ProfileVipTitleType
        else -> -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layout = ConstraintLayout(parent.context)

        layout
            .width(matchParent)
            .height(wrapContent)

        val holder = when (viewType) {
            ProfileVipCardType -> {
                ProfileVipCardViewHolder(layout)
            }
            ProfileVipTitleType -> {
                ProfileVipTitleViewHolder(layout)
            }
            ProfileVipTitle1YearType -> {
                ProfileVip1yearViewHolder(layout)
            }
            else ->
                BaseViewHolder(layout)
        }

        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder) {
            is ProfileVipCardViewHolder -> {
                var image = 0
                var title = ""
                var describe = ""
                var price = ""
                when (position) {
                    0 -> {
                        image = R.drawable.pic_3m
                        title = "Полный доступ на 3 месяца"
                        describe = "Отличный вариант для ознокомления с практикой работы над собой"
                        price = "2499 руб."
                    }
                    1 -> {
                        image = R.drawable.pic_6m
                        title = "Полный доступ на 6 месяцев"
                        describe = "Занимайтесь практикой каждый день, получая"
                        price = "7 999 руб."
                    }
                    else -> {
                        image = R.drawable.pic_1y
                        title = "Полный доступ на год"
                        describe = "Глубокое развитие внутренних качеств человека"
                        price = "18 000 руб."
                    }
                }
                holder.initialize(image, title, describe, price)
            }
            is ProfileVip1yearViewHolder -> {

                holder.initialize(image = R.drawable.pic_1y,
                        title = "Полный доступ на год",
                        describe = "Глубокое развитие внутренних качеств человека",
                        price = "18 000 руб.")
            }
            is ProfileVipTitleViewHolder -> {
                holder.initialize()
            }
        }
    }

}
