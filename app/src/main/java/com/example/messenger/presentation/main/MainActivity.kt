package com.example.messenger.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.core.view.GravityCompat
import com.example.messenger.App
import com.example.messenger.R
import com.example.messenger.base.ABaseActivity
import com.example.messenger.domain.repositories.models.rest.User
import com.example.messenger.presentation.main.dialogue.DialogueFragment
import com.example.messenger.presentation.main.messages.MessagesFragment
import com.example.messenger.service.MessagesService

class MainActivity : ABaseActivity(), IMainActivity {

    companion object {
        fun show(){
            App.appContext.let {
                it.startActivity(Intent(it, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                })
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.container)

        val serviceClass = MessagesService::class.java

        val intent = Intent(this, serviceClass)

        startService(intent)

        if (savedInstanceState != null) return

        showMessages()
    }

    override fun showMessages() {
        replace(MessagesFragment())
    }

    override fun showDialogue(user: User) {
        replace(DialogueFragment(user), "Message")
    }

//    override fun onBackPressed() {
//        if (dlMessages.isDrawerOpen(GravityCompat.START)) dlMessages.closeDrawer(GravityCompat.START)
//        else super.onBackPressed()
//    }
}
