package com.example.ageone.Modules.Registration

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.ageone.Application.R
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextInputLayout.InputEditTextType
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Modules.Registration.rows.RegistrationInputViewHolder
import com.example.ageone.Modules.Registration.rows.initialize
import com.example.ageone.UIComponents.ViewHolders.ButtonViewHolder
import com.example.ageone.UIComponents.ViewHolders.initialize
import yummypets.com.stevia.*

class RegistrationView(initModuleUI: InitModuleUI = InitModuleUI()): BaseModule(initModuleUI) {

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {
        setBackgroundResource(R.drawable.base_background)

        toolBar.title = "Регистрация"
        toolBar.setTitleTextColor(Color.WHITE)

        bodyTable.adapter = viewAdapter
        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER

        innerContent.subviews(
            bodyTable
        )

        bodyTable
            .fillHorizontally()
            .fillVertically()
            .constrainTopToTopOf(innerContent)

    }
}

class Factory(val rootModule: BaseModule): RecyclerView.Adapter<BaseViewHolder>() {

    companion object {
        private const val RegistrationInputType = 0
        private const val RegistrationButtonType = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layout = ConstraintLayout(parent.context)

        layout
            .width(matchParent)
            .height(wrapContent)

        val holder = when(viewType) {
            RegistrationInputType -> {
                RegistrationInputViewHolder(layout)
            }
            RegistrationButtonType -> {
                ButtonViewHolder(layout)
            }
            else ->
                BaseViewHolder(layout)
        }

        return holder
    }

    override fun getItemCount(): Int = 4

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when(holder) {
            is RegistrationInputViewHolder -> {
                when (position) {
                    0 -> holder.initialize("Введите ваше имя и фамилию:", InputEditTextType.TEXT)
                    1 -> holder.initialize("Введите ваш номер телефона:", InputEditTextType.PHONE)
                    else -> holder.initialize("Введите электронную почту:", InputEditTextType.EMAIL)
                }
            }

            is ButtonViewHolder -> {
                holder.initialize("Зарегистрироваться")
            }
        }
    }

    override fun getItemViewType(position: Int):Int = when(position) {
        in 0..2 -> RegistrationInputType
        3 -> RegistrationButtonType
        else -> -1
    }

}