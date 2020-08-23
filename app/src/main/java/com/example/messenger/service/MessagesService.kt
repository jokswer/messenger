package com.example.messenger.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.messenger.App
import com.example.messenger.R
import com.example.messenger.base.SubRX
import com.example.messenger.domain.repositories.MessagesRepository
import javax.inject.Inject


class MessagesService : Service() {
    @Inject
    lateinit var messagesRepository: MessagesRepository

    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable

    private val NOTIFY_ID = 101
    private val CHANNEL_ID = "Messages"


    override fun onCreate() {
        super.onCreate()

        createNotificationChannel()
        App.appComponent.inject(this)

    }

    private fun sendNotification(from: String, message: String) {
        val notification = NotificationCompat.Builder(App.appContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_stat_name)
            .setContentTitle(from)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val notificationManager =
            NotificationManagerCompat.from(App.appContext)

        notificationManager.notify(NOTIFY_ID, notification.build())

    }

    private fun checkNewMessage(){
        messagesRepository.getNewMessages( SubRX { message, e ->
            message?.let {
                message[0].let {
                    sendNotification(it.from.toString(), it.message)
                }
            }

            e?.let {
                return@SubRX
            }
        } )
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }



    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        mHandler = Handler()
        mRunnable = Runnable {
            checkNewMessage()
        }
        mHandler.postDelayed(mRunnable, 5000)

        return START_NOT_STICKY
    }

}
