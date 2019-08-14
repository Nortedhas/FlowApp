package com.example.ageone.Modules.Accaunt.rows

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.External.Base.Button.BaseButton
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import yummypets.com.stevia.subviews

class ButtonViewHolder(itemView: View): BaseViewHolder(itemView){
    var button: BaseButton? = null

    init {
        button = BaseButton()
        button?.let{button ->
            (itemView as ConstraintLayout).subviews(button)

        }
    }

}