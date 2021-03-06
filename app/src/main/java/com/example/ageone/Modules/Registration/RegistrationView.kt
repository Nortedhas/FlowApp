package com.example.ageone.Modules.Registration

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
import com.example.ageone.External.Libraries.Alert.alertManager
import com.example.ageone.External.Libraries.Alert.single
import com.example.ageone.External.Utils.Validation.isValidEmail
import com.example.ageone.External.Utils.Validation.isValidPhone
import com.example.ageone.Models.User.user
import com.example.ageone.Modules.Registration.rows.RegistrationTextHolder
import com.example.ageone.Modules.Registration.rows.initialize
import com.example.ageone.UIComponents.ViewHolders.ButtonViewHolder
import com.example.ageone.UIComponents.ViewHolders.InputViewHolder
import com.example.ageone.UIComponents.ViewHolders.initialize
import yummypets.com.stevia.height
import yummypets.com.stevia.matchParent
import yummypets.com.stevia.width
import yummypets.com.stevia.wrapContent

class RegistrationView(initModuleUI: InitModuleUI = InitModuleUI()): BaseModule(initModuleUI) {

    val viewModel = RegistrationViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {
        setBackgroundResource(R.drawable.base_background)

        toolbar.title = "Регистрация"
        renderToolbar()

        bodyTable.adapter = viewAdapter
        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER

        renderUIO()
    }

    inner class Factory(val rootModule: BaseModule): BaseAdapter<BaseViewHolder>() {

        private val RegistrationInputType = 0
        private val RegistrationButtonType = 1
        private val RegistrationTextType = 2

        override fun getItemCount(): Int = 5

        override fun getItemViewType(position: Int):Int = when(position) {
            in 0..2 -> RegistrationInputType
            3 -> RegistrationButtonType
            4 -> RegistrationTextType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when(viewType) {
                RegistrationInputType -> {
                    InputViewHolder(layout)
                }
                RegistrationButtonType -> {
                    ButtonViewHolder(layout)
                }
                RegistrationTextType -> {
                    RegistrationTextHolder(layout)
                }
                else ->
                    BaseViewHolder(layout)
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
            when(holder) {
                is InputViewHolder -> {
                    when (position) {
                        0 -> {
                            holder.initialize("Введите ваше имя и фамилию:", InputEditTextType.TEXT)
                            holder.textInputL.editText?.doOnTextChanged { text, start, count, after ->
                                viewModel.model.inputName = text.toString()
                            }
                            holder.textInputL.editText?.setText(user.data.name)
                        }
                        1 -> {
                            holder.initialize("Введите ваш номер телефона:", InputEditTextType.PHONE)
                            holder.textInputL.editText?.doOnTextChanged { text, start, count, after ->
                                viewModel.model.inputPhone = text.toString()
                            }
                        }
                        else -> {
                            holder.initialize("Введите электронную почту:", InputEditTextType.EMAIL)
                            holder.textInputL.editText?.doOnTextChanged { text, start, count, after ->
                                viewModel.model.inputMail = text.toString()
                            }
                            holder.textInputL.editText?.setText(user.data.email)
                        }
                    }
                }

                is ButtonViewHolder -> {
                    holder.initialize("Зарегистрироваться")
                    holder.button.setOnClickListener {
                        if (!viewModel.model.inputPhone.isValidPhone()) {
                            alertManager.single("Неверный номер", "Введен неверный номер", null) {_,_ ->
                            }
                        } else if (!viewModel.model.inputMail.isValidEmail()) {
                            alertManager.single("Неверный email", "Введен неверный email", null) {_,_ ->
                            }
                        } else if (viewModel.model.inputName.isBlank()){
                            alertManager.single("Неверное имя", "Имя не введено", null) {_,_ ->
                            }
                        } else {
                            api.request(mapOf(
                                "router" to "phoneAuth",
                                "phone" to viewModel.model.inputPhone),
                                isErrorShown = true){
                                rootModule.emitEvent?.invoke(RegistrationViewModel.EventType.OnRegistrationPressed.toString())
                            }

                        }

                    }
                }

                is RegistrationTextHolder -> {
                    holder.initialize()
                }
            }
        }

    }
}

fun RegistrationView.renderUIO() {

    renderBodyTable()
}