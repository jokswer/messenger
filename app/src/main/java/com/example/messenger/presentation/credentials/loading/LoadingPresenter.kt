package com.example.messenger.presentation.credentials.loading

import android.os.Handler
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.messenger.domain.repositories.UserRepository
import com.example.messenger.presentation.main.MainActivity
import javax.inject.Inject

@InjectViewState
class LoadingPresenter: MvpPresenter<ILoadingView> {
    private val userRepository: UserRepository

    @Inject
    constructor(userRepository: UserRepository){
        this.userRepository = userRepository
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadStaticResources()
    }

    fun loadStaticResources() {
        Handler().postDelayed({
            val user = userRepository.getUser()

            if (user != null) {
                MainActivity.show()
                return@postDelayed
            }

            viewState.showAuth()

        }, 500)

    }
}