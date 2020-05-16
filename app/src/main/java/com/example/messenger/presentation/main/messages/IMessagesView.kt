package com.example.messenger.presentation.main.messages

import com.arellomobile.mvp.MvpView
import com.example.messenger.domain.repositories.models.rest.Message

interface IMessagesView: MvpView {
    fun bindMessages(messages: List<Message>)
}