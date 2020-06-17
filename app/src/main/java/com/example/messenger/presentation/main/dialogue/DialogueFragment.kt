package com.example.messenger.presentation.main.dialogue

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.messenger.App
import com.example.messenger.R
import com.example.messenger.base.ABaseAdapter
import com.example.messenger.base.ABaseListFragment
import com.example.messenger.domain.repositories.models.rest.Message
import com.example.messenger.domain.repositories.models.rest.User
import com.example.messenger.presentation.main.IMainActivity
import kotlinx.android.synthetic.main.fragment_dialogue.*
import javax.inject.Inject

class DialogueFragment(private var user: User) : ABaseListFragment<Message, RecyclerView.ViewHolder>(), IDialogueView {

    @Inject
    @InjectPresenter
    lateinit var presenter: DialoguePresenter

    @ProvidePresenter
    fun providerPresenter() = presenter

    private val adapter = ChatAdapter()

    override fun inject() {
        App.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialogTopBar.title = user.login
        dialogTopBar.setNavigationOnClickListener {
            activity.let {
                if (it is IMainActivity) it.supportFragmentManager.popBackStack()
            }
        }

        btnSendMessage.setOnClickListener {
            val message = etMessage.text.toString()

            if (message.isEmpty()) {
                toast("Введите сообщение")
                return@setOnClickListener
            }

            user.id?.let { it1 -> presenter.sendMessages(message, it1) }
        }

        presenter.getMessages(user.id.toString(), 100, 1)
    }

    override fun getListId(): Int = R.id.rvDialogueList

    override fun provideAdapter(): ABaseAdapter<Message, RecyclerView.ViewHolder> = adapter

    override fun getViewId(): Int = R.layout.fragment_dialogue

    override fun onError(message: String?) {
        message?.let { toast(message) }
    }

    override fun onSuccess(message: String?) {
        message?.let { toast(message) }
    }

    override fun bindMessages(messages: List<Message>) {
        Log.i("test", messages.toString())
        adapter.data = messages.toMutableList()
    }
}