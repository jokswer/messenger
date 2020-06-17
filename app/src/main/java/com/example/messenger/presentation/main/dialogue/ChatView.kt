package com.example.messenger.presentation.main.dialogue

import android.content.Context
import android.util.AttributeSet
import com.example.messenger.R
import com.example.messenger.base.ABaseView
import com.example.messenger.domain.repositories.models.rest.Message
import kotlinx.android.synthetic.main.message.view.*

class ChatView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
): ABaseView(context, attrs, defStyleAttr),
    IChatView {

    override fun getViewId(): Int = R.layout.message

    override fun bind(message: Message) {
        tvFrom.text = message.from.toString()
        tvMessageText.text = message.message
        tvDate.text = message.date
    }
}