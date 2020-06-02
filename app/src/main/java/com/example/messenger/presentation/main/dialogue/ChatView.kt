package com.example.messenger.presentation.main.dialogue

import android.content.Context
import android.util.AttributeSet
import com.example.messenger.R
import com.example.messenger.base.ABaseView

class ChatView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
): ABaseView(context, attrs, defStyleAttr),
    IChatView {

    override fun getViewId(): Int = R.layout.contact
}