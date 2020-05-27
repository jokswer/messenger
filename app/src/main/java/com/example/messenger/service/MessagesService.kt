package com.example.messenger.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import eac.network.Connection

class MessagesService : Service() {

    override fun onBind(intent: Intent): IBinder? {
        return null
    }


}
