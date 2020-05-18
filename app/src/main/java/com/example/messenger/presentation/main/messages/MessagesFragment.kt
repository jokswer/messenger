package com.example.messenger.presentation.main.messages

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.messenger.App
import com.example.messenger.R
import com.example.messenger.base.ABaseAdapter
import com.example.messenger.base.ABaseListFragment
import com.example.messenger.domain.repositories.models.rest.Message
import com.example.messenger.domain.repositories.models.rest.User
import kotlinx.android.synthetic.main.drawer.*
import kotlinx.android.synthetic.main.fragment_messages.*
import javax.inject.Inject

class MessagesFragment : ABaseListFragment<Message, RecyclerView.ViewHolder>(), IMessagesView {

    private val messages = listOf(
        Message("12.12.12", true, 1, 22, "Hello", 2),
        Message("12.12.12", true, 1, 23, "Hello", 2),
        Message("12.12.12", true, 1, 24, "Hello", 2),
        Message("12.12.12", true, 1, 25, "Hello", 2),
        Message("12.12.12", true, 1, 26, "Hello", 2)
    )

    class Adapter: ABaseAdapter<Message, RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val view: View = MessageView(parent.context)

            view.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            return object: RecyclerView.ViewHolder(view) { }
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val view = holder.itemView

            if (view is IMessageView) {
                view.bind(data[position])
            }
        }
    }

    @Inject
    @InjectPresenter
    lateinit var presenter: MessagesPresenter

    @ProvidePresenter
    fun providerPresenter() = presenter

    override fun inject() {
        App.appComponent.inject(this)
    }

    override fun getViewId(): Int = R.layout.fragment_messages
    override fun getListId(): Int = R.id.rvMessagesList

    private val adapter = Adapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        topAppBar.setNavigationOnClickListener {
            dlMessages.openDrawer(Gravity.LEFT)
        }

        btnLogout.setOnClickListener{
            presenter.logout()
        }

        bindUserInfo(presenter.getUserInfo())

        adapter.data = this.messages.toMutableList()
    }

    override fun provideAdapter(): ABaseAdapter<Message, RecyclerView.ViewHolder> = adapter

    override fun bindMessages(messages: List<Message>) {
        adapter.data = messages.toMutableList()
    }

    override fun bindUserInfo(user: User?) {
        tvName.text = user?.login
    }

    override fun onError(message: String?) {
        TODO("Not yet implemented")
    }

}
