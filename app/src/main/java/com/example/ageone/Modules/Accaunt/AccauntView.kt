package com.example.ageone.Modules.Accaunt

import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.ageone.Application.R
import com.example.ageone.External.Base.InitModuleUI
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.ViewHolder.BaseViewHolder
import com.example.ageone.External.Base.ViewHolder.NothingViewHolder
import com.example.ageone.Modules.Accaunt.rows.AccauntButtonViewHolder
import com.example.ageone.Modules.Accaunt.rows.AccauntNameViewHolder
import com.example.ageone.Modules.Accaunt.rows.AccauntPhoneViewHolder
import com.example.ageone.Modules.Accaunt.rows.initialize
import timber.log.Timber
import yummypets.com.stevia.*


class AccauntView(initModuleUI: InitModuleUI = InitModuleUI()): BaseModule(initModuleUI) {

    init {
        /*setBackgroundResource(R.drawable.first_fone)

        val viewAdapter by lazy {
            val viewAdapter = DataAdapter()
            viewAdapter
        }

        bodyTable.adapter = viewAdapter

        innerContent.subviews(
            bodyTable
        )

        bodyTable
            .fillHorizontally()
            .fillVertically()
            .constrainTopToBottomOf(toolBar, 0)*/

    }
}

/*
class DataAdapter: RecyclerView.Adapter<BaseViewHolder>() {

    override fun getItemCount() = 3

    companion object {
        private const val AccauntTitleType = 0
        private const val AccauntPhoneType = 1
        private const val AccauntButtonType = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder{

        val view = ConstraintLayout(parent.context)
        view.height(wrapContent)
        view.width(matchParent)

        return when (viewType) {
            AccauntNameType -> {
                val cell = AccauntNameViewHolder(view)
                cell
            }
            AccauntPhoneType -> {
                val cell = AccauntPhoneViewHolder(view)
                cell
            }
            AccauntButtonType -> {
                val cell = AccauntButtonViewHolder(view)
                cell
            }
            else -> NothingViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder) {
            is AccauntNameViewHolder ->
                holder.initialize("Введите ваше имя и фамилию:")
            is AccauntPhoneViewHolder ->
                holder.initialize("Введите номер телефона:")
            is AccauntButtonViewHolder -> {
                holder.initialize("Press")
                holder.button.setOnClickListener {
                    Timber.i("Hello world")
                }
            }

            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemViewType(position: Int): Int {

        return when (position) {
            0 -> AccauntNameType
            1 -> AccauntPhoneType
            2 -> AccauntButtonType
            else -> -1
        }
    }


}
*/
