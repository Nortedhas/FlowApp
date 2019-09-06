package com.example.ageone.Modules.RegistrationSMS

import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import com.example.ageone.Application.AppActivity
import com.example.ageone.Application.R
import com.example.ageone.Application.api
import com.example.ageone.Application.currentActivity
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.RecyclerView.BaseAdapter
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextInputLayout.InputEditTextType
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Models.User.user
import com.example.ageone.Modules.Meditation.MeditationViewModel
import com.example.ageone.Modules.RegistrationSMS.rows.RegistrationSMSTextViewHolder
import com.example.ageone.Modules.RegistrationSMS.rows.initialize
import com.example.ageone.Modules.RegistrationSMSModel
import com.example.ageone.Modules.RegistrationSMSViewModel
import com.example.ageone.UIComponents.ViewHolders.ButtonViewHolder
import com.example.ageone.UIComponents.ViewHolders.InputViewHolder
import com.example.ageone.UIComponents.ViewHolders.initialize
import yummypets.com.stevia.*

class RegistrationSMSView(initModuleUI: InitModuleUI = InitModuleUI()): BaseModule(initModuleUI) {

    val viewModel = RegistrationSMSViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {

        setBackgroundResource(R.drawable.base_background)

        toolbar.title = "Смс-код"
        renderToolbar()

        bodyTable.adapter = viewAdapter
        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER

        renderUIO()
    }

    inner class Factory(val rootModule: BaseModule): BaseAdapter<BaseViewHolder>() {

        private val RegistrationSMSInputType = 0
        private val RegistrationSMSTextType = 1
        private val RegistrationSMSButtonType = 2

        override fun getItemCount(): Int = 3

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> RegistrationSMSInputType
            1 -> RegistrationSMSTextType
            2 -> RegistrationSMSButtonType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                RegistrationSMSInputType -> {
                    InputViewHolder(layout)
                }
                RegistrationSMSTextType -> {
                    RegistrationSMSTextViewHolder(layout)
                }
                RegistrationSMSButtonType -> {
                    ButtonViewHolder(layout)
                }
                else ->
                    BaseViewHolder(layout)
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
            when (holder) {
                is InputViewHolder -> {
                    holder.initialize("Введите смс-код:", InputEditTextType.NUMERIC)
                    holder.textInputL.editText?.doOnTextChanged { text, start, count, after ->
                        viewModel.model.code = text.toString()
                    }
                }
                is RegistrationSMSTextViewHolder -> {
                    holder.initialize("Если Вы не получили смс, запросить код повторно можно через ")
                }
                is ButtonViewHolder -> {
                    holder.initialize("Подтверждаю")
                    holder.button.setOnClickListener {
                        api.request(mapOf(
                            "router" to "codeCheck",
                            "phone" to viewModel.model.inputPhone,
                            "code" to viewModel.model.code
                        )) {

                        }
                        user.isAuthorized = true
                        rootModule.emitEvent?.invoke(RegistrationSMSViewModel.EventType.OnAcceptPressed.toString())
                    }
                }
            }
        }

    }
}

fun RegistrationSMSView.renderUIO() {
    renderBodyTable()
}
