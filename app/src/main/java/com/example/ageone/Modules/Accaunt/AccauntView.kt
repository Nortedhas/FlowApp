package com.example.ageone.Modules.Accaunt

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ageone.Application.currentActivity
import com.example.ageone.External.Base.Button.BaseButton
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.TextInputLayout.BaseTextInputLayout
import com.example.ageone.External.Base.TextInputLayout.InputEditTextType
import com.example.ageone.External.Base.TextView.BaseTextView
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.recyclerview_item_row.view.*
import yummypets.com.stevia.*


class AccauntView: BaseModule() {

    init {
        setBackgroundColor(Color.LTGRAY)

        val btn by lazy {
            val btn = BaseButton()
            btn.text = "Some"
            btn.setOnClickListener {
                emitEvent?.invoke(AccauntViewModel.EventType.OnPhotoClicked.toString())
            }
            btn
        }

        val textView by lazy {
            val textView = BaseTextView()
            textView.text = "Accaunt"
            textView
        }

        val image by lazy {
            val image = ImageView(currentActivity as Context)
            image
                .height(40F.dp)
                .width(40F.dp)
                .setBackgroundColor(Color.RED)
            image
        }

        val textInputL by lazy {
            val textInputL = BaseTextInputLayout()
            textInputL.hint = "phone"
            textInputL.boxStrokeColor = Color.TRANSPARENT
            textInputL.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_FILLED)
            textInputL.defineType(InputEditTextType.PHONE)
            textInputL.setInactiveUnderlineColor(Color.GREEN)
            textInputL.editText?.textColor = Color.MAGENTA
            textInputL
        }

        val textInputPassword by lazy {
            val textInputL = BaseTextInputLayout()
            textInputL.hint = "password"
            textInputL.boxStrokeColor = Color.MAGENTA
            textInputL.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE)
            textInputL.initPassword()
            textInputL
        }

        val text by lazy {
            val text = BaseTextView()
            text.text = "elevation"
            val shape = GradientDrawable()
            shape.shape = GradientDrawable.RECTANGLE
            shape.setColor(Color.parseColor("#30bcff"))
            text.background = shape
            text.width(25F.dp)
            text.elevation = 8F.dp
            text
        }

        val viewManager by lazy {
            val viewManager = LinearLayoutManager(currentActivity)
            viewManager
        }

        val myDataSet = arrayListOf(btn, textView, image, textInputL, textInputPassword, text)
        val viewAdapter by lazy {
            val viewAdapter = MyAdapter(myDataSet)
            viewAdapter
        }

        val recyclerView by lazy {
            val recyclerView = RecyclerView(currentActivity as Context)
            recyclerView.layoutManager = viewManager
            recyclerView.adapter = viewAdapter
            recyclerView
        }

        innerContent.subviews(
            recyclerView
        )

        recyclerView
            .fillHorizontally()
            .fillVertically()

//        addImageFromGlideWithShadow(image, "https://i.pinimg.com/originals/8c/d2/f5/8cd2f5f7c3b02db7bf60b5ec68d11398.jpg")

    }
}


class MyAdapter(private val views: ArrayList<View>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val inflatedView = parent.inflate(com.example.ageone.Application.R.layout.recyclerview_item_row, false)
        return MyViewHolder(inflatedView)
    }

    override fun getItemCount() = views.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val itemView = views[position]
        holder.bindView(itemView)
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bindView(view: View) {
            itemView.item_layout.subviews(view)
            if (view is BaseTextInputLayout) {
                view.fillHorizontally()
            }
        }
    }

}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}
