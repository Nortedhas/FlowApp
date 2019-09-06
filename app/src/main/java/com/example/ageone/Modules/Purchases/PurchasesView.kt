package com.example.ageone.Modules.Purchases

import android.graphics.Color
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ageone.Application.R
import com.example.ageone.Application.currentActivity
import com.example.ageone.Application.utils
import com.example.ageone.External.Base.Button.BaseButton
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.RecyclerView.BaseAdapter
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Modules.Purchases.rows.PurchasesEmptyViewHolder
import com.example.ageone.Modules.Purchases.rows.initialize
import com.example.ageone.Modules.PurchasesViewModel
import com.example.ageone.UIComponents.ViewHolders.SetViewHolder
import com.example.ageone.UIComponents.ViewHolders.initialize
import yummypets.com.stevia.*


class PurchasesView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = PurchasesViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    val layoutManager by lazy {
        val layoutManager = GridLayoutManager(currentActivity, 2)
        layoutManager
    }

    val linaerManager by lazy {
        val layoutManager = LinearLayoutManager(currentActivity)
        layoutManager
    }

    val bottomBorderM by lazy{
        val imageView = BaseImageView()
        imageView.setBackgroundColor(Color.rgb(0x70,0x7A,0xBA))
        imageView
    }

    val bottomBorderS by lazy{
        val imageView = BaseImageView()
        imageView.setBackgroundColor(Color.rgb(0x70,0x7A,0xBA))
        imageView
    }

    val buttonMed by lazy {
        val button = BaseButton()
        button.text = "Медитации"
        button.textSize = 17F
        button.typeface = Typeface.DEFAULT
        button.textColor = Color.rgb(0x70,0x7A,0xBA)
        button.backgroundColor = Color.TRANSPARENT
        button.initialize()
        button
    }

    val buttonSet by lazy {
        val button = BaseButton()
        button.text = "Сеты"
        button.textSize = 17F
        button.typeface = Typeface.DEFAULT
        button.textColor = Color.rgb(0x70,0x7A,0xBA)
        button.backgroundColor = Color.TRANSPARENT

        button.initialize()
        button
    }

    init {
        setBackgroundResource(R.drawable.base_background)

        toolbar.title = "Покупки"
        renderToolbar()

        bodyTable.layoutManager = linaerManager
        bodyTable.adapter = viewAdapter
//        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER

        bottomBorderS.visibility = View.GONE

        buttonMed.setOnClickListener {
            if (!isMeditationActive) {
                isMeditationActive = !isMeditationActive
                bodyTable.layoutManager = linaerManager
                viewAdapter.notifyDataSetChanged()

                bottomBorderS.visibility = View.GONE
                bottomBorderM.visibility = View.VISIBLE
            }
        }

        buttonSet.setOnClickListener {
            if (isMeditationActive) {
                isMeditationActive = !isMeditationActive
                bodyTable.layoutManager = layoutManager
                viewAdapter.notifyDataSetChanged()

                bottomBorderS.visibility = View.VISIBLE
                bottomBorderM.visibility = View.GONE
            }
        }

        renderUIO()

    }
}

var isMeditationActive = true

fun PurchasesView.renderUIO() {

    innerContent.subviews(
        buttonMed,
        buttonSet,
        bottomBorderM,
        bottomBorderS,
        bodyTable
    )

    buttonMed
        .width((utils.variable.displayWidth - 32) / 2)
        .height(40)
        .constrainTopToTopOf(innerContent, 8)
        .constrainLeftToLeftOf(innerContent, 16)

    buttonSet
        .width((utils.variable.displayWidth - 32) / 2)
        .height(40)
        .constrainTopToTopOf(innerContent, 8)
        .constrainLeftToRightOf(buttonMed, 0)

    bottomBorderM
        .height(1.dp)
        .width(utils.variable.displayWidth / 2)
        .constrainTopToBottomOf(buttonMed)
        .constrainLeftToLeftOf(innerContent)

    bottomBorderS
        .height(1.dp)
        .width(utils.variable.displayWidth / 2)
        .constrainTopToBottomOf(buttonSet)
        .constrainRightToRightOf(innerContent)

    bodyTable
        .fillHorizontally(8)
        .fillVertically()
        .constrainTopToBottomOf(buttonMed, 1.dp)


}

class Factory(val rootModule: BaseModule) : BaseAdapter<BaseViewHolder>() {

    companion object {
        private const val MeditationType = 0
        private const val SetType = 1
    }

    override fun getItemCount(): Int = if (isMeditationActive) 1 else 6

    override fun getItemViewType(position: Int): Int = when (isMeditationActive) {
        true -> MeditationType
        false -> SetType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layout = ConstraintLayout(parent.context)

        layout
            .width(matchParent)
            .height(wrapContent)

        val holder = when (viewType) {
            MeditationType -> {
                PurchasesEmptyViewHolder(layout)
            }
            SetType -> {
                SetViewHolder(layout)
            }
            else ->
                BaseViewHolder(layout)
        }

        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder) {
            is PurchasesEmptyViewHolder -> {
                holder.initialize()
            }
            is SetViewHolder -> {
                holder.initialize(
                    utils.variable.displayWidth / 2 - 8, R.drawable.kitty,
                    "Спокойствие", "Медитация для тех кто проснулся и уже встал.", position, position + 1)
                holder.constraintLayout.setOnClickListener {
                    rootModule.emitEvent?.invoke(PurchasesViewModel.EventType.OnSetPressed.toString())
                }
            }
        }
    }

}
