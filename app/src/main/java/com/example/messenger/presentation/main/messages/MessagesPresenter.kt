package com.example.messenger.presentation.main.messages

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.messenger.base.SubRX
import com.example.messenger.domain.repositories.UserRepository
import com.example.messenger.presentation.credentials.CredentialsActivity
import java.io.File
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

    fun uploadAvatar(file: File){

        userRepository.uploadAvatar(SubRX { path, e ->
            e?.let {
                viewState.onError(it.localizedMessage)
                Log.e("tag", it.message)
                it.printStackTrace()
                return@SubRX
            }

            path?.let {
                println("Link: $it")
                viewState.onSuccess(it.path)
            }

        }, file)
    }
}
