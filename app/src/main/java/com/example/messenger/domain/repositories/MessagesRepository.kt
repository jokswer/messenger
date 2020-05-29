package com.example.messenger.domain.repositories

import com.example.messenger.base.SubRX
import com.example.messenger.base.standardSubscribeIO
import com.example.messenger.domain.repositories.models.rest.UploadedFile
import com.example.messenger.domain.repositories.models.rest.User
import com.example.messenger.domain.repositories.rest.api.MessagesRestApi
import java.io.File
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

    fun uploadAvatar(observer: SubRX<UploadedFile>, file: File) {
        rest.uploadAvatar(file)
            .standardSubscribeIO(observer)
    }
}