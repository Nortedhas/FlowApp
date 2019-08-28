package com.example.ageone.Modules.MeditationFilter

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ageone.Application.R
import com.example.ageone.Application.currentActivity
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.RecyclerView.BaseAdapter
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextView.BaseTextView
import com.example.ageone.External.Base.View.BaseView
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Modules.MeditationFilter.rows.*
import com.example.ageone.Modules.MeditationFilterViewModel
import com.example.ageone.UIComponents.ViewHolders.TitleViewHolder
import com.example.ageone.UIComponents.ViewHolders.initialize
import yummypets.com.stevia.*

class MeditationFilterView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    val layoutManager by lazy {
        val layoutManager = GridLayoutManager(currentActivity, 2)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (position) {
                    in 0..2, 13 -> 2
                    else -> 1
                }
            }
        }
        layoutManager
    }

    val rectangleUp by lazy {
        val view = BaseView()
        view.backgroundColor = Color.rgb(0x98, 0x9E, 0xDD)
        view.cornerRadius = 30.dp
        view.initialize()
        view
    }

    val rectangleDown by lazy {
        val view = BaseView()
        view.backgroundColor = Color.rgb(0x98, 0x9E, 0xDD)
        view.initialize()
        view
    }

    val textViewSearch by lazy {
        val textView = BaseTextView()
        textView.text = "Подобрать медитацию"
        textView.gravity = Gravity.CENTER
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textSize = 21F
        textView.textColor = Color.WHITE
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }

    init {
        setBackgroundResource(R.drawable.back_filter)

        toolbar.title = "Подбор медитации"
        renderToolbar()

        bodyTable.layoutManager = layoutManager
        bodyTable.adapter = viewAdapter
        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER


        rectangleUp.setOnClickListener {
            emitEvent?.invoke(MeditationFilterViewModel.EventType.OnSearchPressed.toString())
        }

        renderUIO()

    }
}

fun MeditationFilterView.renderUIO() {

    renderBodyTable()

    innerContent.subviews(
        rectangleUp,
        rectangleDown,
        textViewSearch
    )

    rectangleUp
        .fillHorizontally()
        .constrainBottomToBottomOf(innerContent)
        .height(60)

    rectangleDown
        .fillHorizontally()
        .constrainTopToTopOf(rectangleUp, 30)
        .height(30)

    textViewSearch
        .constrainCenterYToCenterYOf(rectangleUp)
        .constrainLeftToLeftOf(rectangleUp)
        .constrainRightToRightOf(rectangleUp)

}

class Factory(val rootModule: BaseModule) : BaseAdapter<BaseViewHolder>() {

    companion object {
        private const val MeditationFilterTitleType = 0
        private const val MeditationFilterTimeButtonType = 1
        private const val MeditationFilterGoalType = 2
        private const val MeditationFilterEmptyType = 3
    }

    override fun getItemCount(): Int = 14

    override fun getItemViewType(position: Int): Int = when (position) {
        0, 2 -> MeditationFilterTitleType
        1 -> MeditationFilterTimeButtonType
        in 3..12 -> MeditationFilterGoalType
        13 -> MeditationFilterEmptyType
        else -> -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layout = ConstraintLayout(parent.context)

        layout
            .width(matchParent)
            .height(wrapContent)

        val holder = when (viewType) {
            MeditationFilterTitleType -> {
                TitleViewHolder(layout)
            }
            MeditationFilterTimeButtonType -> {
                MeditationFilterTimeButtonViewHolder(layout)
            }
            MeditationFilterGoalType -> {
                MeditationFilterGoalViewHolder(layout)
            }
            MeditationFilterEmptyType -> {
                MeditationFilterEmptyViewHolder(layout)
            }
            else ->
                BaseViewHolder(layout)
        }

        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder) {
            is TitleViewHolder -> {
                val title = if (position == 0) "Выберите длительность медитации:" else "Выберите цель медитации:"
                holder.initialize(title, Color.WHITE)
            }
            is MeditationFilterTimeButtonViewHolder -> {
                holder.initialize()
            }
            is MeditationFilterGoalViewHolder -> {
                val text = if (position - 3 < goals.size) goals[position - 3] else ""
                holder.initialize(text)
            }
            is MeditationFilterEmptyViewHolder -> {
                holder.initialize()
            }
        }
    }

    private val goals = arrayListOf(
        "Я в безопасности",
        "Принять себя",
        "Денежный поток",
        "Женские Энергии",
        "Мужские Энергии",
        "От боли",
        "На природе",
        "Интуиция",
        "Я прощаю",
        "Лишний вес"
        )

}
