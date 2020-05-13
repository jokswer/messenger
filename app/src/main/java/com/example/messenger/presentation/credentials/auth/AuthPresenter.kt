package com.example.messenger.presentation.credentials.auth

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.messenger.base.SubRX
import com.example.messenger.domain.repositories.UserRepository
import com.example.messenger.presentation.main.MainActivity
import javax.inject.Inject

@InjectViewState
class AuthPresenter: MvpPresenter<IAuthView> {

    private var userRepository: UserRepository

    @Inject
    constructor(userRepository: UserRepository){
        this.userRepository = userRepository
    }

    fun auth(login: String, password: String) {
        userRepository.login(SubRX { _, e ->
            if (e != null) {
                viewState.onError(e.localizedMessage)
                e.printStackTrace()
                return@SubRX
            }

            MainActivity.show()
        }, login, password)
    }
}