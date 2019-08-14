package com.example.ageone.Modules.Auth

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.ageone.Application.R
import com.example.ageone.External.Base.InitModuleUI
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.ViewHolder.BaseViewHolder
import com.example.ageone.External.Base.ViewHolder.NothingViewHolder
import com.example.ageone.Modules.Auth.rows.*
import timber.log.Timber
import yummypets.com.stevia.*

class AuthView(initModuleUI: InitModuleUI = InitModuleUI()): BaseModule(initModuleUI) {

    init {
        setBackgroundResource(R.drawable.first_fone)

        val viewAdapter by lazy {
            val viewAdapter = DataAdapter()
            viewAdapter
        }

        bodyTable.adapter = viewAdapter
        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER

        bodyTable.setItemViewCacheSize(4)
        bodyTable.setDrawingCacheEnabled(true)
        bodyTable.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH)

        innerContent.subviews(
            bodyTable
        )

        bodyTable
            .fillHorizontally()
            .fillVertically()
            .constrainTopToBottomOf(toolBar, 0)

    }

}

class DataAdapter: RecyclerView.Adapter<BaseViewHolder>() {

    override fun getItemCount() = 52

    companion object {
        private const val AuthTitleType = 0
        private const val AuthEnterPhoneType = 1
        private const val AuthEnterSocialType = 2

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        val view = ConstraintLayout(parent.context)
        view
            .height(wrapContent)
            .width(matchParent)

        return when (viewType) {
            AuthTitleType -> {
                val cell = AuthTitleViewHolder(view)
                cell
            }

            AuthEnterPhoneType -> {
                val cell = AuthEnterPhoneViewHolder(view)
                cell
            }

            AuthEnterSocialType -> {
                val cell = AuthEnterSocialViewHolder(view)
                cell
            }
            else -> NothingViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder) {
            is AuthTitleViewHolder -> {
                holder.initialize(R.drawable.logo, "Добро пожаловать\nв Поток", "Познай себя и свои энергии")
            }

            is AuthEnterPhoneViewHolder -> {
                holder.initialize("Вход в приложение", "По номеру телефона")
            }

            is AuthEnterSocialViewHolder -> {
                if (position % 2 == 0) {
                    holder.initialize("Вход через vk", R.drawable.vk, Color.parseColor("#4A76A8"))
                } else if (position % 2 == 1) {
                    holder.initialize("Вход через facebook", R.drawable.facebook, Color.parseColor("#3B5998"))
                }
            }

            else -> Timber.i("Something going wrong")
        }
    }

    override fun getItemViewType(position: Int): Int {

        return when (position) {
            0 -> AuthTitleType
            1 -> AuthEnterPhoneType
            in (2..52) -> AuthEnterSocialType
            else -> -1
        }
    }


}
