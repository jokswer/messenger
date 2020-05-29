package com.example.messenger.domain.repositories.rest.api

import com.example.messenger.base.ABaseRestApi
import com.example.messenger.base.IRestClient
import com.example.messenger.domain.repositories.models.rest.User
import com.example.messenger.domain.repositories.rest.service.IMessagesRestApiService
import io.reactivex.Observable
import javax.inject.Inject

class MessagesRestApi: ABaseRestApi<IMessagesRestApiService> {

    @Inject
    constructor(client: IRestClient): super(client)

    fun getUsers(): Observable<List<User>> {
        return service.getUsers()
    }
}