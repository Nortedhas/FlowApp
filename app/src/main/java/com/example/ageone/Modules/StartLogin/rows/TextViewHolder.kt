package com.example.ageone.Modules.StartLogin.rows

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextView.BaseTextView
import yummypets.com.stevia.subviews

class TextViewHolder(itemView: View): BaseViewHolder(itemView){
    var text: BaseTextView? = null

    init {
        text = BaseTextView()
        text?.let{text ->
            (itemView as ConstraintLayout).subviews(text)

        }
    }

}