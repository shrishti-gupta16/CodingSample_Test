package com.example.codingsample.utils

import android.content.Context
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.ViewSwitcher

class TextViewFactory(
    private val context: Context,
    private val center: Boolean
) : ViewSwitcher.ViewFactory {
    override fun makeView(): View {
        val textView = TextView(context)
        if (center) {
            textView.gravity = Gravity.CENTER
        }
        return textView
    }

}