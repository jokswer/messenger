package com.example.messenger.presentation.credentials.auth

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.messenger.App
import com.example.messenger.R
import com.example.messenger.base.ABaseFragment
import com.example.messenger.presentation.credentials.ICredentialsActivity
import kotlinx.android.synthetic.main.fragment_auth.*
import javax.inject.Inject

class AuthFragment : ABaseFragment(), IAuthView {

    @Inject
    @InjectPresenter
    lateinit var presenter: AuthPresenter

    @ProvidePresenter
    fun providerPresenter() = presenter

    override fun inject() {
        App.appComponent.inject(this)
    }

    override fun getViewId(): Int = R.layout.fragment_auth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnOnRegistration.setOnClickListener {
            activity.let {
                if (it is ICredentialsActivity) it.showRegistration()
            }
        }

        btnAuth.setOnClickListener {
            val login = "${tiLogin.text}"
            val password = "${tiPassword.text}"

            if (login.isEmpty() || password.isEmpty()) {
                toast(R.string.loginOrPasswordEmptyError)
                return@setOnClickListener
            }

            presenter.auth(login,password)
        }

    }
}
