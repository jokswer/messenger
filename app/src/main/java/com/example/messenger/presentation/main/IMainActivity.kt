package com.example.messenger.presentation.main

import com.example.messenger.domain.repositories.models.rest.User

interface IMainActivity {
    fun showMessages()
    fun showDialogue(user: User)
}