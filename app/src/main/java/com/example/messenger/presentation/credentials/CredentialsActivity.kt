package com.example.messenger.presentation.credentials

import android.os.Bundle
import com.example.messenger.R
import com.example.messenger.base.ABaseActivity
import com.example.messenger.presentation.credentials.auth.AuthFragment
import com.example.messenger.presentation.credentials.registration.RegistrationFragment


class CredentialsActivity : ABaseActivity(), ICredentialsActivity {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.container)

        showAuth()
    }

    override fun showAuth() {
        replace(AuthFragment())
    }

    override fun showRegistration() {
        replace(RegistrationFragment())
    }

//    override fun showLoading() {
//        replace()
//    }

}
