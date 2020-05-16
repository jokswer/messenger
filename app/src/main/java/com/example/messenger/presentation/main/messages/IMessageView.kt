package com.example.messenger.presentation.main.messages

import com.example.messenger.domain.repositories.models.rest.Message

interface IMessageView {
    fun bind(data: Message)
}