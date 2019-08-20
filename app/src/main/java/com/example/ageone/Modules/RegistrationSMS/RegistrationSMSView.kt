package com.example.ageone.Modules

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
import com.example.ageone.Modules.RegistrationSMS.rows.RegistrationSMSInputViewHolder
import com.example.ageone.Modules.RegistrationSMS.rows.initialize
import yummypets.com.stevia.*

class RegistrationSMSView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {
        setBackgroundResource(R.drawable.base_background)

        toolBar.title = ""
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

class Factory(val rootModule: BaseModule) : RecyclerView.Adapter<BaseViewHolder>() {

    companion object {
        private const val RegistrationSMSInputType = 0
    }

    override fun getItemCount(): Int = 1

    override fun getItemViewType(position: Int): Int = when (position) {
        0 -> RegistrationSMSInputType
        else -> -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layout = ConstraintLayout(parent.context)

        layout
            .width(matchParent)
            .height(wrapContent)

        val holder = when (viewType) {
            RegistrationSMSInputType -> {
                RegistrationSMSInputViewHolder(layout)
            }
            else ->
                BaseViewHolder(layout)
        }

        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder) {
            is RegistrationSMSInputViewHolder -> {
                holder.initialize("Введите смс-код:", InputEditTextType.NUMERIC)
            }
        }
    }

}
