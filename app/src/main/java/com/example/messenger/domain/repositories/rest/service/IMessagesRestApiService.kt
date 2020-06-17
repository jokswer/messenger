package com.example.messenger.domain.repositories.rest.service

import com.example.messenger.domain.repositories.models.rest.Message
import com.example.messenger.domain.repositories.models.rest.UploadedFile
import com.example.messenger.domain.repositories.models.rest.User
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.*

interface IMessagesRestApiService {

    @GET("/user/v1/users")
    fun getUsers(): Observable<List<User>>


    @Multipart
    @POST("/upload/v1/avatar")
    fun uploadAvatar(@Part file: MultipartBody.Part): Observable<UploadedFile>

    @GET("/messenger/v1/messages")
    fun getMessages(@Query("from") from: String, @Query("limit") limit: Int, @Query("page") page: Int ): Observable<List<Message>>

    @GET("/messenger/v1/new_messages")
    fun getNewMessages(): Observable<List<Message>>

    @POST("/messenger/v1/send")
    fun sendMessage(@Body message: Message): Observable<Message>

    @GET("/messenger/v1/online")
    fun getOnline()
}