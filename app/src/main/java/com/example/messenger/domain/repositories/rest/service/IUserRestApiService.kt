package com.example.messenger.domain.repositories.rest.service

import com.example.messenger.domain.repositories.models.rest.Token
import com.example.messenger.domain.repositories.models.rest.User
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*

interface IUserRestApiService {

    @PUT("/user/v1/registration")
    fun registration(@Body user: User): Observable<User>

    @POST("/user/v1/login")
    fun login(@Body user: User): Observable<User>

    @POST("/user/v1/refresh")
    fun refreshToken(
        @Header("refresh_token") refreshToken: String
    ): Call<Token>
}