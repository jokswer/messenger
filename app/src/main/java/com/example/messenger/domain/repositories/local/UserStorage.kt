package com.example.messenger.domain.repositories.local

import com.example.messenger.domain.repositories.models.realm.TokenRealm
import com.example.messenger.domain.repositories.models.realm.UserRealm
import com.example.messenger.domain.repositories.models.rest.Token
import com.example.messenger.domain.repositories.models.rest.User
import com.example.messenger.domain.repositories.models.toBase
import com.example.messenger.domain.repositories.models.toRealm
import io.realm.Realm
import javax.inject.Inject

class UserStorage {

    private var user: User? = null
    private var token: Token? = null

    @Inject
    constructor()

    fun save(user: User) {
        this.user = user

        Realm.getDefaultInstance().use {
            it.executeTransaction { realm ->
                user.toRealm()?.let { realm.copyToRealmOrUpdate(it) }
            }
        }
    }

    fun save(token: Token) {
        user?.token = token

        Realm.getDefaultInstance().use {
            it.executeTransaction {realm ->
                it.where(UserRealm::class.java).findFirst()?.let {
                    it.token = token.toRealm()
                    realm.copyToRealmOrUpdate(it)
                }
            }
        }
    }

    fun getUser(): User? {
        user?.let {
            return it
        }

        Realm.getDefaultInstance().use {
            return it.where(UserRealm::class.java).findFirst()?.toBase().apply { user = this }
        }
    }

    fun removeUser(){
        user = null

        Realm.getDefaultInstance().use {
            it.executeTransaction { realm ->
                it.where(UserRealm::class.java).findAll().deleteAllFromRealm()
                it.where(TokenRealm::class.java).findAll().deleteAllFromRealm()
            }
        }
    }

    fun getToken(): Token? {

        token?.let {
            return it
        }

        Realm.getDefaultInstance().use {
            return it.where(TokenRealm::class.java).findFirst()?.toBase().apply { token = this }
        }
    }
}