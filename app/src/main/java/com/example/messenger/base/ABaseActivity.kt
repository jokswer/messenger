package com.example.messenger.base

import androidx.fragment.app.Fragment
import com.arellomobile.mvp.MvpAppCompatActivity
import com.example.messenger.R

abstract class ABaseActivity: MvpAppCompatActivity() {
    open fun getContainer(): Int = R.id.container

    fun replace(fragment: Fragment, backStack: String? = null, tag: String? = null ){
        supportFragmentManager.beginTransaction()
            .replace(getContainer(), fragment, tag).apply {
                backStack?.let { addToBackStack(it) }
            }
            .commit()
    }
}