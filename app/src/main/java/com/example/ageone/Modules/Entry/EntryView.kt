package com.example.ageone.Modules.Entry

import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import com.example.ageone.Application.R
import com.example.ageone.Application.api
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.RecyclerView.BaseAdapter
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextInputLayout.InputEditTextType
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Modules.Entry.rows.EntryTextViewHolder
import com.example.ageone.Modules.Entry.rows.initialize
import com.example.ageone.Modules.EntryViewModel
import com.example.ageone.UIComponents.ViewHolders.ButtonViewHolder
import com.example.ageone.UIComponents.ViewHolders.InputViewHolder
import com.example.ageone.UIComponents.ViewHolders.initialize
import yummypets.com.stevia.*

class EntryView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = EntryViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {
        setBackgroundResource(R.drawable.base_background)

        toolbar.title = "Вход в Поток"
        renderToolbar()

        bodyTable.adapter = viewAdapter
        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER

        renderUIO()
    }
    inner class Factory(val rootModule: BaseModule): BaseAdapter<BaseViewHolder>() {

        private val EntryInputType = 0
        private val EntryButtonType = 1
        private val EntryTextType = 2

        override fun getItemCount(): Int = 3

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> EntryInputType
            1 -> EntryButtonType
            2 -> EntryTextType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                EntryInputType -> {
                    InputViewHolder(layout)
                }
                EntryButtonType -> {
                    ButtonViewHolder(layout)
                }
                EntryTextType -> {
                    EntryTextViewHolder(layout)
                }
                else ->
                    BaseViewHolder(layout)
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
            when (holder) {
                is InputViewHolder -> {
                    holder.initialize("Введите ваш номер телефона:", InputEditTextType.PHONE)
                    holder.textInputL.editText?.doOnTextChanged { text, start, count, after ->
                        viewModel.model.inputPhone = text.toString()
                    }
                }

                is ButtonViewHolder -> {
                    holder.initialize("Войти в приложение")
                    holder.button.setOnClickListener {
                        api.request(mapOf(
                            "router" to "phoneAuth",
                            "phone" to viewModel.model.inputPhone,
                            "isAuth" to true)){
                            rootModule.emitEvent?.invoke(EntryViewModel.EventType.OnEnterPressed.toString())
                        }
                    }
                }

                is EntryTextViewHolder -> {
                    holder.initialize()
                }
            }
        }

    }
}

fun EntryView.renderUIO() {

    renderBodyTable()
}


