package com.example.messenger.domain.repositories

import com.example.messenger.base.SubRX
import com.example.messenger.base.standardSubscribeIO
import com.example.messenger.domain.repositories.models.rest.User
import com.example.messenger.domain.repositories.rest.api.MessagesRestApi
import javax.inject.Inject

class MessagesRepository {

    private val rest: MessagesRestApi

    @Inject
    constructor(rest: MessagesRestApi){
        this.rest = rest
    }

    fun getUsers(observer: SubRX<List<User>>) {

        rest.getUsers()
            .standardSubscribeIO(observer)
    }
}