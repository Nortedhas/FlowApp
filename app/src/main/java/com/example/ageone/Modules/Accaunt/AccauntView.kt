package com.example.ageone.Modules.Accaunt

import android.graphics.Color
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.Modules.Accaunt.rows.ButtonViewHolder
import com.example.ageone.Modules.Accaunt.rows.TextViewHolder
import yummypets.com.stevia.constrainTopToBottomOf
import yummypets.com.stevia.fillHorizontally
import yummypets.com.stevia.fillVertically
import yummypets.com.stevia.subviews


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
            .constrainTopToBottomOf(toolBar)

    }
}


class MyAdapter: RecyclerView.Adapter<BaseViewHolder>() {

    companion object {
        private const val ButtonType = 0
        private const val TextType = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        val layout = ConstraintLayout(parent.context)

        val holder = when(viewType) {
            0 -> {
                ButtonViewHolder(layout)
            }
            else -> {
                TextViewHolder(layout)
            }
        }

        return holder
    }

    override fun getItemViewType(position: Int): Int {
        return position % 2
    }

    override fun getItemCount() = 30

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

        when(holder) {
            is ButtonViewHolder -> {
                holder.button?.text = "$position"
                holder.button?.setOnClickListener {

                }
            }
            is TextViewHolder -> {
                holder.text?.text = "Text $position"
            }
        }

    }





}
