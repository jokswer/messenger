package com.example.messenger.base

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

abstract class ABaseView constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    abstract fun getViewId(): Int

    init {
        val view = inflate(context, getViewId(), this)
    }
}