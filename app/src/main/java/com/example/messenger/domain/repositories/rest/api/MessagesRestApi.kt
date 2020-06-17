package com.example.messenger.domain.repositories.rest.api

import com.example.messenger.base.ABaseRestApi
import com.example.messenger.base.IRestClient
import com.example.messenger.domain.repositories.models.rest.Message
import com.example.messenger.domain.repositories.models.rest.UploadedFile
import com.example.messenger.domain.repositories.models.rest.User
import com.example.messenger.domain.repositories.rest.service.IMessagesRestApiService
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.MultipartBody
import java.io.File
import javax.inject.Inject

class MessagesRestApi: ABaseRestApi<IMessagesRestApiService> {

    @Inject
    constructor(client: IRestClient): super(client)

    fun getUsers(): Observable<List<User>> {
        return service.getUsers()
    }

    fun uploadAvatar(file: File): Observable<UploadedFile> {

        val part = MultipartBody.Part.createFormData("file",
            file.name + ".jpg",
            MultipartBody.create(MediaType.parse("image/*"), file)
        )

        return service.uploadAvatar(part)

    }

    fun getNewMessages(): Observable<List<Message>> {
        return service.getNewMessages()
    }

    fun getMessages(from: String, limit: Int, page: Int): Observable<List<Message>> {
        return service.getMessages(from,  limit, page)
    }

    fun sendMessage(message: String, to: Int): Observable<Message> {
        return service.sendMessage(Message(
            message = message,
            to = to
        ))
    }
}