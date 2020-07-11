package com.oxygen.freecorona.util

import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.oxygen.freecorona.R

object ErrorUiUtils {

    fun showErrorSnackbar(v: View, text: String) {
        val view = v.findViewById(R.id.activity_main_coordinator) ?: v
        if (view is ViewGroup) {
            val snackbar = Snackbar.make(view, text, Snackbar.LENGTH_LONG)

            view.addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
                override fun onViewAttachedToWindow(v: View) {
                }

                override fun onViewDetachedFromWindow(v: View) {
                    snackbar.dismiss()
                }
            })

            snackbar.show()
        }
    }
}