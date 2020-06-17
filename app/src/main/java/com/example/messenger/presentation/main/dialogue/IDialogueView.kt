package com.example.messenger.presentation.main.dialogue

import com.example.messenger.base.IBaseView
import com.example.messenger.domain.repositories.models.rest.Message

interface IDialogueView: IBaseView {
    fun bindMessages(messages: List<Message>)
}