package com.example.messenger.presentation.credentials.registration

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.messenger.presentation.main.MainActivity
import javax.inject.Inject

@InjectViewState
class RegistrationPresenter: MvpPresenter<IRegistrationView> {

    @Inject
    constructor(){

    }

    fun login(login: String, password: String){

        MainActivity.show()
    }
}