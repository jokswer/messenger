package com.example.messenger.domain.repositories.rest.service

import com.example.messenger.domain.repositories.models.rest.User
import io.reactivex.Observable
import retrofit2.http.GET

interface IMessagesRestApiService {

    @GET("/user/v1/users")
    fun getUsers(): Observable<List<User>>
}