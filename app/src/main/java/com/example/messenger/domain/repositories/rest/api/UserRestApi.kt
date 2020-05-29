package com.example.messenger.domain.repositories.rest.api

import com.example.messenger.base.ABaseRestApi
import com.example.messenger.base.IRestClient
import com.example.messenger.domain.di.modules.NetModule
import com.example.messenger.domain.repositories.models.rest.User
import com.example.messenger.domain.repositories.rest.service.IUserRestApiService
import javax.inject.Inject
import javax.inject.Named

class UserRestApi: ABaseRestApi<IUserRestApiService> {

    @Inject
    constructor(@Named(NetModule.NAME_AUTH_REST_CLIENT) client: IRestClient) : super(client)

    fun login(login: String, password: String)
            = service.login(
        User(
            login = login,
            password = password
        )
    )

    fun registration(login: String, password: String)
            = service.registration(
        User(
            login = login,
            password = password
        )
    )

    fun refreshToken(refreshToken: String)
            = service.refreshToken(refreshToken)
}