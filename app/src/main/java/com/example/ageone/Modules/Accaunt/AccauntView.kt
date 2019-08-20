package com.example.ageone.Modules.Accaunt

import android.graphics.Color
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.ageone.Application.R
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.Modules.Accaunt.rows.*
import yummypets.com.stevia.*


class AccauntView: BaseModule() {

    init {
        setBackgroundColor(Color.LTGRAY)

        val viewAdapter by lazy {
            val viewAdapter = MyAdapter()
            viewAdapter
        }

        bodyTable.adapter = viewAdapter

        innerContent.subviews(
            bodyTable
        )

        bodyTable
            .fillVertically()
            .fillHorizontally()
            .constrainTopToTopOf(innerContent)
//            .constrainTopToBottomOf(toolBar)

    }
}


class MyAdapter: RecyclerView.Adapter<BaseViewHolder>() {

    companion object {
        private const val EnterTitleType = 0
        private const val EnterButtonType = 1
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
            else -> {
                TextViewHolder(layout)
            }
        }

//        (holder as EnterTitleViewHolder).imageViewPhoto?.setImageResource(R.drawable.logo)

        return holder
    }

    override fun getItemViewType(position: Int):Int = when(position % 2) {
        0 -> EnterTitleType
        1 -> EnterButtonType
        else -> -1
    }

    override fun getItemCount() = 20

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
            }
        }

    }

}