package com.example.messenger.presentation.credentials.registration

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.messenger.base.SubRX
import com.example.messenger.domain.repositories.UserRepository
import com.example.messenger.presentation.main.MainActivity
import javax.inject.Inject

@InjectViewState
class RegistrationPresenter: MvpPresenter<IRegistrationView> {

    private var userRepository: UserRepository

    @Inject
    constructor(userRepository: UserRepository){
        this.userRepository = userRepository
    }

    fun login(login: String, password: String){

        userRepository.registration(SubRX { _, e ->

            if (e != null) {
                viewState.onError(e.localizedMessage)
                e.printStackTrace()
                return@SubRX
            }

            MainActivity.show()

        }, login, password)
    }
}