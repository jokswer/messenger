package com.example.messenger.base

import com.arellomobile.mvp.MvpView

interface IBaseView: MvpView {
    fun onError(message: String?)
    fun onSuccess(message: String?)
}