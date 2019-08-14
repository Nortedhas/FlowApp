package com.example.ageone.External.Base.RecyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.ageone.Application.currentActivity

class BaseRecyclerView: RecyclerView(currentActivity as Context) {

}

open class BaseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

}