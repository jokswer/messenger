package com.example.messenger.domain.repositories.rest.service

import com.example.messenger.domain.repositories.models.rest.UploadedFile
import com.example.messenger.domain.repositories.models.rest.User
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface IMessagesRestApiService {

    @GET("/user/v1/users")
    fun getUsers(): Observable<List<User>>


    @Multipart
    @POST("/upload/v1/avatar")
    fun uploadAvatar(@Part file: MultipartBody.Part): Observable<UploadedFile>
}