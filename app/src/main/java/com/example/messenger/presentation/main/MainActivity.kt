package com.example.messenger.presentation.main

import android.content.Intent
import android.os.Bundle
import com.example.messenger.App
import com.example.messenger.R
import com.example.messenger.base.ABaseActivity

class MainActivity : ABaseActivity() {

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
        setContentView(R.layout.activity_main)
    }
}
