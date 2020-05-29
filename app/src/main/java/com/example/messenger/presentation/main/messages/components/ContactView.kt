package com.example.messenger.presentation.main.messages.components

import android.content.Context
import android.util.AttributeSet
import com.example.messenger.R
import com.example.messenger.base.ABaseView
import com.example.messenger.domain.repositories.models.rest.User
import kotlinx.android.synthetic.main.contact.view.*

class ContactView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
): ABaseView(context, attrs, defStyleAttr),
    IContactView {

    override fun getViewId(): Int = R.layout.contact

    override fun bind(data: User) {
        tvContactName.text = data.login
    }
}