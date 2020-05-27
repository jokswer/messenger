package com.example.messenger.presentation.credentials.registration

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.messenger.App
import com.example.messenger.R
import com.example.messenger.base.ABaseFragment
import com.example.messenger.presentation.credentials.ICredentialsActivity
import kotlinx.android.synthetic.main.fragment_registration.*
import javax.inject.Inject

class RegistrationFragment : ABaseFragment(), IRegistrationView {

    @Inject
    @InjectPresenter
    lateinit var presenter: RegistrationPresenter

    @ProvidePresenter
    fun providerPresenter() = presenter

    override fun inject() {
        App.appComponent.inject(this)
    }

    override fun getViewId(): Int = R.layout.fragment_registration

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnOnAuth.setOnClickListener {
            activity.let {
                if (it is ICredentialsActivity) it.showAuth()
            }
        }

        btnLogin.setOnClickListener{
            val login = "${tiLogin.text}"
            val password = "${tiPassword.text}"

            if (login.isEmpty() || password.isEmpty()) {
                toast(R.string.loginOrPasswordEmptyError)
                return@setOnClickListener
            }

            presenter.login(login,password)
        }

    }

    override fun onError(message: String?) {
        message?.let { toast(it) }
    }

    override fun onSuccess(message: String?) {
        TODO("Not yet implemented")
    }
}
