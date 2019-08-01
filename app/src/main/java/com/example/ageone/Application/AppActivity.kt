package com.example.ageone.Application

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.example.ageone.External.Base.Activity.BaseActivity
import yummypets.com.stevia.subviews

class AppActivity: BaseActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        // for launchScreen
        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE


        coordinator.setLaunchScreen()


        setContentView(router.layout)

    }

}

fun AppActivity.setStatusBarColor(color: Int) {
    window.statusBarColor = color
}