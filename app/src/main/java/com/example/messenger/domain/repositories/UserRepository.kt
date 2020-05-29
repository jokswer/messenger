package com.example.messenger.domain.repositories

import android.os.SystemClock
import com.example.messenger.base.SubRX
import com.example.messenger.base.standardSubscribeIO
import com.example.messenger.domain.repositories.local.UserStorage
import com.example.messenger.domain.repositories.models.rest.Token
import com.example.messenger.domain.repositories.models.rest.UploadedFile
import com.example.messenger.domain.repositories.models.rest.User
import com.example.messenger.domain.repositories.rest.api.UserRestApi
import java.io.File
import java.net.HttpURLConnection
import javax.inject.Inject

class UserRepository {

    private val rest: UserRestApi
    private val storage: UserStorage

    @Inject
    constructor(rest: UserRestApi, storage: UserStorage){
        this.rest = rest
        this.storage = storage
    }

    fun login(observer: SubRX<User>, login: String, password: String) {
        rest.login(login, password)
            .doOnNext { storage.save(it) }
            .standardSubscribeIO(observer)
    }

    fun registration(observer: SubRX<User>, login: String, pass: String) {
        rest.registration(login, pass)
            .doOnNext { storage.save(it) }
            .standardSubscribeIO(observer)
    }

    fun logout() {
        storage.removeUser()
    }

    fun getUser() = storage.getUser()

    fun getToken() = storage.getToken()

    fun refreshToken(token: Token, onRetry: (Int) -> Boolean = { it != HttpURLConnection.HTTP_UNAUTHORIZED }): Token? {

        val response = rest.refreshToken(token.refresh).execute()
        if (response.isSuccessful)
            response.body()?.let {
                it.refresh = token.refresh
                storage.save(it)
                return it
            }

        if (onRetry(response.code())) {
            SystemClock.sleep(500)
            return refreshToken(token)
        }

        return null
    }
}