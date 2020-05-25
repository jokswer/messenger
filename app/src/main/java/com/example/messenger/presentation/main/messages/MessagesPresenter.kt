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
        Log.i("tag", "presenter")
        userRepository.uploadAvatar(SubRX { path, e ->
            Log.i("tag", path.toString())
            e?.let {
                viewState.onError(it.localizedMessage)
                it.printStackTrace()
                return@SubRX
            }

        }, file)
    }
}
