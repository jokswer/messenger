package com.example.messenger.presentation.main.messages

import com.example.messenger.base.IBaseView
import com.example.messenger.domain.repositories.models.rest.Message
import com.example.messenger.domain.repositories.models.rest.User

interface IMessagesView: IBaseView {
    fun bindUserInfo(user: User?)
    fun bindContacts(users: List<User> )
    fun bindMessages(messages: List<Message>)
}