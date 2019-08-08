package com.example.ageone.Application

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.ageone.External.Base.Activity.BaseActivity
import com.example.ageone.External.Libraries.Glide.GlideApp
import com.example.ageone.Network.HTTP.Methods
import com.swarmnyc.promisekt.Promise
import timber.log.Timber
import yummypets.com.stevia.subviews

class AppActivity: BaseActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        // for launchScreen
        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        /*//download cache
        GlideApp
            .with( this )
            .download("https://i.pinimg.com/originals/8c/d2/f5/8cd2f5f7c3b02db7bf60b5ec68d11398.jpg")
            .diskCacheStrategy(DiskCacheStrategy.ALL)*/

        coordinator.setLaunchScreen()
        Promise<Unit> { resolve, _ ->

            router.layout.setOnApplyWindowInsetsListener { _, insets ->
                utils.variable.statusBarHeight = insets.systemWindowInsetTop

                resolve(Unit)
                insets
            }

        }.then {
            Methods.handshake()
        }.then {
            coordinator.start()
        }

        setContentView(router.layout)

    }

}

fun AppActivity.setStatusBarColor(color: Int) {
    window.statusBarColor = color
}