package com.example.messenger.presentation.main.dialogue

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.messenger.base.SubRX
import com.example.messenger.domain.repositories.MessagesRepository
import javax.inject.Inject

@InjectViewState
class DialoguePresenter: MvpPresenter<IDialogueView> {

    private var messagesRepository: MessagesRepository

    @Inject
    constructor(messagesRepository: MessagesRepository){
        this.messagesRepository = messagesRepository
    }

    fun getMessages(from: String, limit: Int, page: Int) {
        messagesRepository.getMessages( SubRX { messages, e ->
            messages?.let {
                viewState.bindMessages(messages)
            }

            e?.let {
                viewState.onError(e.localizedMessage)
                return@SubRX
            }
        }, from, limit, page)
    }

    fun sendMessages(message: String, to: Int) {
        messagesRepository.sendMessage( SubRX { message, e ->

            e?.let {
                viewState.onError(e.localizedMessage)
                return@SubRX
            }
        }, message, to )
    }
}