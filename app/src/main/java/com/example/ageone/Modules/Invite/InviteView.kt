package com.example.ageone.Modules.Invite

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ageone.Application.R
import com.example.ageone.Application.currentActivity
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.RecyclerView.BaseAdapter
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Modules.Invite.rows.InviteCardViewHolder
import com.example.ageone.Modules.Invite.rows.initialize
import com.example.ageone.UIComponents.ViewHolders.ButtonViewHolder
import com.example.ageone.UIComponents.ViewHolders.initialize
import yummypets.com.stevia.*

class InviteView (initModuleUI: InitModuleUI = InitModuleUI()): BaseModule(initModuleUI) {

    val viewModel = InviteViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    val layoutManager by lazy {
        val layoutManager = GridLayoutManager(currentActivity, 2)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (position) {
                    in 0..3 -> 2
                    else -> 1
                }
            }
        }
        layoutManager
    }


    init {
        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.back_invite)

        toolbar.title = "Пригласи друга"

        renderToolbar()

        bodyTable.layoutManager = layoutManager
        bodyTable.adapter = viewAdapter
        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER

        renderUIO()
        bindUI()
    }

    fun bindUI() {

    }


    inner class Factory(val rootModule: BaseModule): BaseAdapter<BaseViewHolder>() {

        private val InviteCard = 0
        private val InviteButton = 1

        override fun getItemCount() = 2

        override fun getItemViewType(position: Int):Int = when(position) {
            0 -> InviteCard
            1 -> InviteButton
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when(viewType) {
                InviteCard -> {
                    InviteCardViewHolder(layout)
                }
                InviteButton -> {
                    ButtonViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when(holder) {
                is InviteCardViewHolder -> {
                    holder.initialize()
                }
                is ButtonViewHolder -> {
                    holder.initialize("Пригласить друга")
                    holder.button.setOnClickListener {
                        val sendIntent: Intent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
                            type = "text/plain"
                        }

                        val shareIntent = Intent.createChooser(sendIntent, null)
                        currentActivity?.startActivity(shareIntent)
                    }
                }
            }

        }

    }

}

fun InviteView.renderUIO() {

    renderBodyTable()
    bodyTable
        .fillHorizontally(0)
}