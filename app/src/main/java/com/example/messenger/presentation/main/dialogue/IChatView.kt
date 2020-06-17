package com.example.messenger.presentation.main.dialogue

import com.example.messenger.domain.repositories.models.rest.Message

interface IChatView {
    fun bind(message: Message)
}