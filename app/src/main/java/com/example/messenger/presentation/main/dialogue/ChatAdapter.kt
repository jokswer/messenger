package com.example.messenger.presentation.main.dialogue

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.messenger.base.ABaseAdapter
import com.example.messenger.domain.repositories.models.rest.Message

class ChatAdapter: ABaseAdapter<Message, RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            ChatView(
                parent.context
            )

        view.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        return object: RecyclerView.ViewHolder(view) { }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val view = holder.itemView

        if (view is IChatView) {

        }
    }
}