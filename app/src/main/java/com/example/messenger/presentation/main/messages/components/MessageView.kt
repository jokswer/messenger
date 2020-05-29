package com.example.messenger.presentation.main.messages.components

import android.content.Context
import android.util.AttributeSet
import com.example.messenger.R
import com.example.messenger.base.ABaseView
import com.example.messenger.domain.repositories.models.rest.Message
import kotlinx.android.synthetic.main.message.view.*

class MessageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
): ABaseView(context, attrs, defStyleAttr),
    IMessageView {

    override fun getViewId(): Int = R.layout.message

    override fun bind(data: Message) {
        tvFrom.text = data.from.toString()
        tvMessageText.text = data.message
        tvDate.text = data.date
    }
}