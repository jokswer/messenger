package com.example.messenger.presentation.main.messages

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.messenger.domain.repositories.UserRepository
import com.example.messenger.presentation.credentials.CredentialsActivity
import javax.inject.Inject

@InjectViewState
class MessagesPresenter : MvpPresenter<IMessagesView> {

    private var userRepository: UserRepository

    @Inject
    constructor(userRepository: UserRepository){
        this.userRepository = userRepository
    }

    fun logout() {
        userRepository.logout()
        CredentialsActivity.show()
    }

    fun getUserInfo() = userRepository.getUser()
}
