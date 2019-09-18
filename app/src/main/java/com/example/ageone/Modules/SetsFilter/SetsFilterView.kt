package com.example.ageone.Modules.SetsFilter

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
import com.example.ageone.Modules.SetsFilter.rows.SetsFilterEmptyViewHolder
import com.example.ageone.Modules.SetsFilter.rows.SetsFilterRuneViewHolder
import com.example.ageone.Modules.SetsFilter.rows.initialize
import com.example.ageone.Modules.SetsFilterViewModel
import com.example.ageone.UIComponents.ViewHolders.TitleViewHolder
import com.example.ageone.UIComponents.ViewHolders.initialize
import timber.log.Timber
import yummypets.com.stevia.*

class SetsFilterView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = SetsFilterViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    val layoutManager by lazy {
        val layoutManager = GridLayoutManager(currentActivity, 4)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (position) {
                    0 -> 4
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
        textView.text = "Подобрать сет"
        textView.gravity = Gravity.CENTER
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textSize = 21F
        textView.textColor = Color.WHITE
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }

    init {
        setBackgroundResource(R.drawable.back_filter)

        toolbar.title = "Подбор сета"
        renderToolbar()

        bodyTable.layoutManager = layoutManager
        bodyTable.adapter = viewAdapter
        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER

        rectangleUp.setOnClickListener {
            emitEvent?.invoke(SetsFilterViewModel.EventType.OnSearchPressed.toString())
        }

        renderUIO()

    }

}

fun SetsFilterView.renderUIO() {
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
        private const val SetsFilterTitleType = 0
        private const val SetsFilterRuneType = 1
        private const val SetsFilterEmptyType = 2
    }

    override fun getItemCount(): Int = 14

    override fun getItemViewType(position: Int): Int = when (position) {
        0 -> SetsFilterTitleType
        in 1..12 -> SetsFilterRuneType
        13 -> SetsFilterEmptyType
        else -> -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layout = ConstraintLayout(parent.context)

        layout
            .width(matchParent)
            .height(wrapContent)

        val holder = when (viewType) {
            SetsFilterTitleType -> {
                TitleViewHolder(layout)
            }
            SetsFilterRuneType -> {
                SetsFilterRuneViewHolder(layout)
            }
            SetsFilterEmptyType -> {
                SetsFilterEmptyViewHolder(layout)
            }
            else ->
                BaseViewHolder(layout)
        }

        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder) {
            is TitleViewHolder -> {
                holder.initialize("Выберите руну, которая вам больше всего понравилась", Color.WHITE, 17F)
            }
            is SetsFilterRuneViewHolder -> {
                val rune = if (position - 1 < runes.size) runes[position - 1] else 0
                holder.initialize(rune, position == selectedRune)
                holder.constraintLayout.setOnClickListener {
                    Timber.i("rune")
                    selectedRune = position
                    notifyDataSetChanged()
                }
            }
            is SetsFilterEmptyViewHolder -> {
                holder.initialize()
            }
        }
    }

    private var selectedRune = -1

    private val runes = arrayListOf(
        R.drawable.rune1,
        R.drawable.rune2,
        R.drawable.rune3,
        R.drawable.rune4,
        R.drawable.rune5,
        R.drawable.rune6,
        R.drawable.rune7,
        R.drawable.rune8,
        R.drawable.rune9,
        R.drawable.rune10,
        R.drawable.rune11,
        R.drawable.rune12
        )
}
