package com.example.messenger.presentation.credentials.auth

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.messenger.presentation.main.MainActivity
import javax.inject.Inject

@InjectViewState
class AuthPresenter: MvpPresenter<IAuthView> {

    @Inject
    constructor(){

    }

    fun auth(login: String, password: String) {

        MainActivity.show()
    }
}