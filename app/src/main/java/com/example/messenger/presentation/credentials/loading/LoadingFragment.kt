package com.example.messenger.presentation.credentials.loading

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.messenger.App
import com.example.messenger.R
import com.example.messenger.base.ABaseFragment
import com.example.messenger.presentation.credentials.ICredentialsActivity
import javax.inject.Inject

class LoadingFragment: ABaseFragment(), ILoadingView {

    @Inject
    @InjectPresenter
    lateinit var presenter: LoadingPresenter

    @ProvidePresenter
    fun providerPresenter() = presenter

    override fun inject() {
        App.appComponent.inject(this)
    }

    override fun getViewId(): Int = R.layout.fragment_loading

    override fun showAuth() {
        activity?.let {
            if (it is ICredentialsActivity)
                it.showAuth()
        }
    }

    override fun onError(message: String?) {
        TODO("Not yet implemented")
    }

}