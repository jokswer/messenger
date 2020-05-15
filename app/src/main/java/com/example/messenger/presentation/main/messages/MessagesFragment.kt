package com.example.messenger.presentation.main.messages

import android.os.Bundle
import android.view.Gravity
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.messenger.App
import com.example.messenger.R
import com.example.messenger.base.ABaseFragment
import kotlinx.android.synthetic.main.drawer.*
import kotlinx.android.synthetic.main.fragment_messages.*
import javax.inject.Inject

class MessagesFragment : ABaseFragment(), IMessagesView {

    @Inject
    @InjectPresenter
    lateinit var presenter: MessagesPresenter

    @ProvidePresenter
    fun providerPresenter() = presenter

    override fun inject() {
        App.appComponent.inject(this)
    }

    override fun getViewId(): Int = R.layout.fragment_messages

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        topAppBar.setNavigationOnClickListener {
            navigation.openDrawer(Gravity.LEFT)
        }

        btnLogout.setOnClickListener{
            presenter.logout()
        }
    }

    override fun onError(message: String?) {
        TODO("Not yet implemented")
    }

}
