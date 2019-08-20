package com.example.ageone.Modules.Start

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ageone.Application.R
import com.example.ageone.Application.currentActivity
import com.example.ageone.External.Base.Button.BaseButton
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextView.BaseTextView
import com.example.ageone.External.InitModuleUI
import yummypets.com.stevia.*
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.ageone.External.Base.RecyclerView.CirclePagerIndicatorDecoration
import com.example.ageone.Models.User.user

class StartView(initModuleUI: InitModuleUI = InitModuleUI()): BaseModule(initModuleUI) {

    val textViewHello by lazy {
        val textViewHello = BaseTextView()
        textViewHello.gravity = Gravity.CENTER
        textViewHello.typeface = Typeface.DEFAULT_BOLD
        textViewHello.textSize = 29F
        textViewHello.textColor = Color.WHITE
        textViewHello.setBackgroundColor(Color.TRANSPARENT)
        textViewHello.text = "Добро пожаловать\nв Поток "
        textViewHello
    }

    val imageView by lazy {
        val imageView = BaseImageView()
        imageView
            .width(96)
            .height(95)
        imageView.setImageResource(R.drawable.logo)
        imageView.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN)
        imageView
    }


    val buttonEnter by lazy {
        val button = BaseButton()
        button.textSize = 17F
        button.textColor = Color.WHITE
        button.typeface = Typeface.DEFAULT
        button.backgroundColor = Color.parseColor("#8B91C7")
        button.cornerRadius = 60
        button.text = "Вход в приложение"
        button.initialize()
        button
    }

    init {
        setBackgroundResource(R.drawable.first)

        bodyTable.adapter = Factory()
        bodyTable.layoutManager = LinearLayoutManager(currentActivity, LinearLayoutManager.HORIZONTAL, false)
        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER
        bodyTable.addItemDecoration(CirclePagerIndicatorDecoration())

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(bodyTable)

        buttonEnter.setOnClickListener {
            user.isAuthorized = true
            emitEvent?.invoke(StartViewModel.EventType.OnEnterPressed.toString())
        }

        renderUIO()
    }

}

fun StartView.renderUIO() {
    innerContent.subviews(
        imageView,
        textViewHello,
        bodyTable,
        buttonEnter
    )

    buttonEnter
        .constrainBottomToBottomOf(innerContent, 40)
        .fillHorizontally(32)

    bodyTable
        .constrainBottomToTopOf(buttonEnter, 72)
        .constrainLeftToLeftOf(innerContent)
        .constrainRightToRightOf(innerContent)

    textViewHello
        .constrainBottomToTopOf(bodyTable, 56)
        .constrainLeftToLeftOf(innerContent)
        .constrainRightToRightOf(innerContent)

    imageView
        .constrainBottomToTopOf(textViewHello, 40)
        .constrainLeftToLeftOf(innerContent)
        .constrainRightToRightOf(innerContent)
}

class Factory: RecyclerView.Adapter<Factory.TextHolder>() {
    private val list = listOf("Повседневная практика показывает, что новая модель организационной деятельности",
        "Повседневная практика показывает, что новая модель организационной деятельности",
        "Повседневная практика показывает, что новая модель организационной деятельности")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextHolder {
        val layout = ConstraintLayout(parent.context)

        layout
            .width(matchParent)
            .height(90)//CONST?

        return TextHolder(layout)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: TextHolder, position: Int) {
        holder.textView.text = list[position]
    }

    class TextHolder(constraintLayout: ConstraintLayout): BaseViewHolder(constraintLayout) {
        val textView by lazy {
            val textViewSmall = BaseTextView()
            textViewSmall.gravity = Gravity.CENTER
            textViewSmall.typeface = Typeface.DEFAULT
            textViewSmall.textSize = 19F
            textViewSmall.textColor = Color.WHITE
            textViewSmall.setBackgroundColor(Color.TRANSPARENT)
            textViewSmall
        }

        init {
            constraintLayout.subviews(
                textView
            )

            textView
                /*.constrainLeftToLeftOf(constraintLayout, 50)
                .constrainRightToRightOf(constraintLayout, 50)*/
                .constrainTopToTopOf(constraintLayout)
                .centerHorizontally()
                .width(320)
        }


    }

}