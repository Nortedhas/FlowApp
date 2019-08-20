package com.example.ageone.Modules.StartLogin

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.ageone.Application.R
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Models.User.user
import com.example.ageone.Modules.Accaunt.rows.*
import com.example.ageone.Modules.StartLogin.rows.EnterLoginTextViewHolder
import com.example.ageone.Modules.StartLogin.rows.EnterSocialViewHolder
import com.example.ageone.Modules.StartLogin.rows.initialize
import timber.log.Timber
import yummypets.com.stevia.*


class StartLoginView(initModuleUI: InitModuleUI = InitModuleUI()): BaseModule(initModuleUI) {

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {
        setBackgroundResource(R.drawable.first)

        bodyTable.adapter = viewAdapter
        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER
        //TODO: смена позиции каждые 5 сек

        innerContent.subviews(
            bodyTable
        )

        bodyTable
            .fillVertically()
            .fillHorizontally()
            .constrainTopToTopOf(innerContent)
    }
}


class Factory(val rootModule: BaseModule): RecyclerView.Adapter<BaseViewHolder>() {//TODO отнаследовать от BaseRecycler

    companion object {
        private const val EnterTitleType = 0
        private const val EnterButtonType = 1
        private const val EnterSocialType = 2
        private const val EnterLoginTextType = 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        val layout = ConstraintLayout(parent.context)

        layout
            .width(matchParent)
            .height(wrapContent)

        val holder = when(viewType) {
            EnterTitleType -> {
                EnterTitleViewHolder(layout)
            }
            EnterButtonType -> {
                EnterButtonViewHolder(layout)
            }
            EnterSocialType -> {
                EnterSocialViewHolder(layout)
            }
            EnterLoginTextType -> {
                EnterLoginTextViewHolder(layout)
            }
            else -> {
                TextViewHolder(layout)
            }
        }

        return holder
    }

    override fun getItemViewType(position: Int):Int = when(position % 5) {
        0 -> EnterTitleType
        1 -> EnterButtonType
        2,3 -> EnterSocialType
        4 -> EnterLoginTextType
        else -> -1
    }

    override fun getItemCount() = 5

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

        when(holder) {
            is ButtonViewHolder -> {
                holder.button?.text = "$position"
                holder.button?.setOnClickListener {

                }
            }
            is EnterTitleViewHolder -> {
                holder.initialize("Добро пожаловать\nв Поток", "Познай себя и свои энергии", R.drawable.logo)
            }
            is EnterButtonViewHolder -> {
                holder.initialize("Вход в приложение", "По номеру телефона")
                holder.buttonEnterPhone.setOnClickListener {
                    rootModule.emitEvent?.invoke(StartLoginViewModel.EventType.OnRegistrationPhonePressed.toString())
                }
            }
            is EnterSocialViewHolder -> {
                when (position % 2) {
                    0 ->{
                        holder.initialize("Вход через vk", R.drawable.vk,
                            Color.parseColor("#4A76A8"), Pair(26F, 15F))
                        holder.enterButton.setOnClickListener {
                            user.isAuthorized = true
                            Timber.i("${user.isAuthorized}")
                            rootModule.emitEvent?.invoke(StartLoginViewModel.EventType.OnVkPressed.toString())
                        }
                    }

                    else -> {
                        holder.initialize("Вход через facebook", R.drawable.facebook,
                            Color.parseColor("#3B5998"), Pair(10.8F, 20F))
                    }

                }
            }
            is EnterLoginTextViewHolder -> {
                holder.initialize("Уже зарегистрированы? Войти тут")
            }
        }

    }

}
