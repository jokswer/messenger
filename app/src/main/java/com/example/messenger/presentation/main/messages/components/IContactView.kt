package com.example.messenger.presentation.main.messages.components

import com.example.messenger.domain.repositories.models.rest.User
import com.example.messenger.presentation.main.messages.OnContactsListener

interface IContactView {
    fun bind(data: User, OnContactsListener: OnContactsListener)
}