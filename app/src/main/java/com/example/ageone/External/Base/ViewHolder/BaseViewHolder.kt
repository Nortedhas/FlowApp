package com.example.ageone.External.Base.ViewHolder

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var constraintLayout: ConstraintLayout? = null
}

class NothingViewHolder(itemView: View) : BaseViewHolder(itemView) {

    init {
    }

}