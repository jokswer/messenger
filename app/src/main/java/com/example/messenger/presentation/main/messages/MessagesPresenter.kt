package com.example.messenger.presentation.main.messages

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.messenger.base.SubRX
import com.example.messenger.domain.repositories.MessagesRepository
import com.example.messenger.domain.repositories.UserRepository
import com.example.messenger.presentation.credentials.CredentialsActivity
import java.io.File
import javax.inject.Inject

@InjectViewState
class MessagesPresenter : MvpPresenter<IMessagesView> {

    private var userRepository: UserRepository
    private var messagesRepository: MessagesRepository

    @Inject
    constructor(userRepository: UserRepository, messagesRepository: MessagesRepository){
        this.userRepository = userRepository
        this.messagesRepository = messagesRepository
    }

    fun logout() {
        userRepository.logout()
        CredentialsActivity.show()
    }

    fun getUserInfo() = userRepository.getUser()

    fun uploadAvatar(file: File){

        messagesRepository.uploadAvatar(SubRX { path, e ->
            e?.let {
                viewState.onError(it.localizedMessage)
                it.printStackTrace()
                return@SubRX
            }

            path?.let {
                viewState.onSuccess(it.path)
            }

        }, file)
    }

    fun getUsers() {
        messagesRepository.getUsers(SubRX { users, e ->
            users?.let { viewState.bindContacts(users) }
            e?.printStackTrace()
        })
    }
}
