package com.example.ageone.External.Base.RecyclerView

import android.app.Activity
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.ageone.Application.currentActivity

class BaseRecyclerView: RecyclerView(currentActivity as Context) {

}