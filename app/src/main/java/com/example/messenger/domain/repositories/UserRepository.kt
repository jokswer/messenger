package com.example.messenger.domain.repositories

import android.util.Log
import com.example.messenger.base.SubRX
import com.example.messenger.base.standardSubscribeIO
import com.example.messenger.domain.repositories.models.rest.User
import com.example.messenger.domain.repositories.rest.api.UserRestApi
import javax.inject.Inject

class UserRepository {

    private var rest: UserRestApi

    @Inject
    constructor(rest: UserRestApi){
        this.rest = rest
    }

    fun login(observer: SubRX<User>, login: String, password: String) {
        rest.login(login, password)
//            .doOnNext { storage.save(it) }
            .standardSubscribeIO(observer)
    }

    fun registration(observer: SubRX<User>, login: String, pass: String) {
        rest.registration(login, pass)
            .standardSubscribeIO(observer)
    }
}