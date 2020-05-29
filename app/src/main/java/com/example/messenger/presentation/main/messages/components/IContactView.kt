package com.example.messenger.presentation.main.messages.components

import com.example.messenger.domain.repositories.models.rest.User

interface IContactView {
    fun bind(data: User)
}