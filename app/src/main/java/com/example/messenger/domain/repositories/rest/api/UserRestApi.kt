package com.example.messenger.domain.repositories.rest.api

import com.example.messenger.base.ABaseRestApi
import com.example.messenger.base.IRestClient
import com.example.messenger.domain.di.modules.NetModule
import com.example.messenger.domain.repositories.models.rest.UploadedFile
import com.example.messenger.domain.repositories.models.rest.User
import com.example.messenger.domain.repositories.rest.service.IUserRestApiService
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.MultipartBody
import java.io.File
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

    fun uploadAvatar(file: File): Observable<UploadedFile> {

        val part = MultipartBody.Part.createFormData("file",
            file.name + ".jpg",
            MultipartBody.create(MediaType.parse("image/*"), file)
        )

        return service.uploadAvatar(part)

    }
}