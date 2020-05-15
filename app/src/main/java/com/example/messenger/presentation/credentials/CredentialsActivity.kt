package com.example.messenger.presentation.credentials

import android.content.Intent
import android.os.Bundle
import com.example.messenger.App
import com.example.messenger.R
import com.example.messenger.base.ABaseActivity
import com.example.messenger.domain.repositories.local.UserStorage
import com.example.messenger.presentation.credentials.auth.AuthFragment
import com.example.messenger.presentation.credentials.loading.LoadingFragment
import com.example.messenger.presentation.credentials.registration.RegistrationFragment


class CredentialsActivity : ABaseActivity(), ICredentialsActivity {

    companion object {

        private const val REMOVE_USER = "REMOVE_USER"

        fun show() {
            App.appContext.let {
                it.startActivity(Intent(it, CredentialsActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    putExtra(REMOVE_USER, true)
                })
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.container)

        if (savedInstanceState != null) return

        if (intent.getBooleanExtra(REMOVE_USER, false)) {
            UserStorage().removeUser()
            showAuth()
            return
        }

        showLoading()
    }

    override fun showAuth() {
        replace(AuthFragment())
    }

    override fun showRegistration() {
        replace(RegistrationFragment(), "Registration")
    }

    override fun showLoading() {
        replace(LoadingFragment())
    }

}
