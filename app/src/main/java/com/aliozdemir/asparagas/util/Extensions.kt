package com.aliozdemir.asparagas.util

import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

fun View.applySystemInsetsPadding(
    applyLeft: Boolean = true,
    applyTop: Boolean = true,
    applyRight: Boolean = true,
    applyBottom: Boolean = true,
) {
    ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->
        val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        val left = if (applyLeft) systemBars.left else 0
        val top = if (applyTop) systemBars.top else 0
        val right = if (applyRight) systemBars.right else 0
        val bottom = if (applyBottom) systemBars.bottom else 0
        view.setPadding(left, top, right, bottom)
        insets
    }
}

fun View.applySystemInsetsMargin(
    applyLeft: Boolean = false,
    applyTop: Boolean = false,
    applyRight: Boolean = false,
    applyBottom: Boolean = true,
) {
    ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->
        val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        val params = view.layoutParams as? ViewGroup.MarginLayoutParams
        params?.let {
            it.leftMargin = if (applyLeft) systemBars.left else it.leftMargin
            it.topMargin = if (applyTop) systemBars.top else it.topMargin
            it.rightMargin = if (applyRight) systemBars.right else it.rightMargin
            it.bottomMargin = if (applyBottom) systemBars.bottom else it.bottomMargin
            view.layoutParams = it
        }
        insets
    }
}
