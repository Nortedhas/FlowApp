package com.example.ageone.Modules.CodeInvitation

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.Application.R
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.RecyclerView.BaseAdapter
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextInputLayout.InputEditTextType
import com.example.ageone.External.InitModuleUI
import com.example.ageone.UIComponents.ViewHolders.ButtonViewHolder
import com.example.ageone.UIComponents.ViewHolders.InputViewHolder
import com.example.ageone.UIComponents.ViewHolders.initialize
import yummypets.com.stevia.height
import yummypets.com.stevia.matchParent
import yummypets.com.stevia.width
import yummypets.com.stevia.wrapContent

class CodeInvitationView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = CodeInvitationViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {

        setBackgroundResource(R.drawable.base_background)

        toolbar.title = "Код-приглашение"

        renderToolbar()

        bodyTable.adapter = viewAdapter
        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER

        renderUIO()
        bindUI()
    }

    fun bindUI() {

    }

    inner class Factory(val rootModule: BaseModule) : BaseAdapter<BaseViewHolder>() {

        private val InputType = 0
        private val ButtonType = 1

        override fun getItemCount() = 2

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> InputType
            1 -> ButtonType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                InputType -> {
                    InputViewHolder(layout)
                }
                ButtonType -> {
                    ButtonViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is InputViewHolder -> {
                    holder.initialize("Введите код-приглашение", InputEditTextType.TEXT)
                }
                is ButtonViewHolder -> {
                    holder.initialize("Получить VIP-доступ")
                }

            }

        }

    }

}

fun CodeInvitationView.renderUIO() {

    renderBodyTable()
}


