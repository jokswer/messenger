package com.example.messenger.presentation.main.dialogue

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.messenger.App
import com.example.messenger.base.ABaseAdapter
import com.example.messenger.base.ABaseListFragment
import com.example.messenger.domain.repositories.models.rest.Message
import javax.inject.Inject

class DialogueFragment: ABaseListFragment<Message, RecyclerView.ViewHolder>(), IDialogueView {

    @Inject
    @InjectPresenter
    lateinit var presenter: DialoguePresenter

    @ProvidePresenter
    fun providerPresenter() = presenter

    override fun inject() {
        App.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun getListId(): Int {
        TODO("Not yet implemented")
    }

    override fun provideAdapter(): ABaseAdapter<Message, RecyclerView.ViewHolder> {
        TODO("Not yet implemented")
    }

    override fun getViewId(): Int {
        TODO("Not yet implemented")
    }

    override fun onError(message: String?) {
        TODO("Not yet implemented")
    }

    override fun onSuccess(message: String?) {
        TODO("Not yet implemented")
    }
}