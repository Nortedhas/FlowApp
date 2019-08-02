package com.example.ageone.External.Libraries.Alert

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.Typeface
import android.media.Image
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.example.ageone.Application.App
import com.example.ageone.Application.currentActivity
import com.example.ageone.External.Base.TextView.BaseTextView
import timber.log.Timber
import yummypets.com.stevia.*

val alertManager
    get() = AlertManager()

class AlertManager {

    fun renderUI() {

        constraintLayout.subviews(
            imageViewIcon,
            textViewTitle,
            textViewMessage
        )

        imageViewIcon
            .constrainTopToTopOf(constraintLayout, 8)
            .constrainLeftToLeftOf(constraintLayout, 16)
            .height(20F.dp)
            .width(20F.dp)

        textViewTitle
            .fillHorizontally()
            .constrainLeftToLeftOf(constraintLayout, 16)
            .constrainTopToBottomOf(imageViewIcon, 8)
            .constrainRightToRightOf(constraintLayout, 16)

        textViewMessage
            .fillHorizontally()
            .constrainLeftToLeftOf(constraintLayout, 16)
            .constrainTopToBottomOf(textViewTitle, 8)
            .constrainRightToRightOf(constraintLayout, 16)

    }

    // MARK: UI

    val constraintLayout: ConstraintLayout by lazy {
        val constraintLayout = ConstraintLayout(currentActivity)
        constraintLayout
    }

    val imageViewIcon: ImageView by lazy {
        val imageViewIcon = ImageView(currentActivity)
        imageViewIcon
    }

    // MARK: textViewTitle

    val textViewTitle: BaseTextView by lazy {
        val textViewTitle = BaseTextView()
        textViewTitle.gravity = View.TEXT_ALIGNMENT_VIEW_END
        textViewTitle.typeface = Typeface.DEFAULT_BOLD
        textViewTitle.textSize = 6F.dp
        textViewTitle.textColor = Color.BLACK
        textViewTitle.setBackgroundColor(Color.TRANSPARENT)
        textViewTitle

    }

    // MARK: textViewMessage

    val textViewMessage: BaseTextView by lazy {
        val textViewMessage = BaseTextView()
        textViewMessage.gravity = View.TEXT_ALIGNMENT_VIEW_END
        textViewMessage.typeface = Typeface.DEFAULT_BOLD
        textViewMessage.textSize = 5F.dp
        textViewMessage.textColor = Color.DKGRAY
        textViewMessage.setBackgroundColor(Color.TRANSPARENT)
        textViewMessage
    }

}

// MARK: Single

fun AlertManager.single(title: String, message: String, image: Int? = null,
                        button: String = "OK", completion: ((DialogInterface, Int) -> (Unit))? = null) {
    renderUI()

    if (image == null) {
        imageViewIcon
            .height(0)
            .width(0)
            .constrainTopToTopOf(constraintLayout, 0)
    } else {
        imageViewIcon.setImageResource(image)
    }

    textViewTitle.text = title
    textViewMessage.text = message

    val builder = AlertDialog.Builder(currentActivity)
    builder.setView(constraintLayout)
    completion?.let{
        builder.setPositiveButton(button, completion)
    }
    builder.show()

}

// MARK: Double

fun AlertManager.double(
    title: String, message: String, image: Int? = null,
    button: String = "OK", completion: (DialogInterface, Int) -> (Unit),
    buttonCancel: String = "CANCEL", completionCancel: ((DialogInterface, Int) -> (Unit))? = null
) {

    renderUI()

    if (image == null) {
        imageViewIcon
            .height(0)
            .width(0)
            .constrainTopToTopOf(constraintLayout, 0)
    } else {
        imageViewIcon.setImageResource(image)
    }

    textViewTitle.text = title
    textViewMessage.text = message

    val builder = AlertDialog.Builder(currentActivity)
    builder.setView(constraintLayout)
    builder.setPositiveButton(button, completion)
    completionCancel?.let {
        builder.setNegativeButton(buttonCancel, completionCancel)
    }
    builder.show()
}