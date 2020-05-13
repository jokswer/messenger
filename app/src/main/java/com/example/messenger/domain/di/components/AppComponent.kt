package com.example.messenger.domain.di.components

import com.example.messenger.domain.di.modules.NetModule
import com.example.messenger.presentation.credentials.auth.AuthFragment
import com.example.messenger.presentation.credentials.loading.LoadingFragment
import com.example.messenger.presentation.credentials.registration.RegistrationFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetModule::class])

interface AppComponent {
    fun inject(target: RegistrationFragment)
    fun inject(target: AuthFragment)
    fun inject(target: LoadingFragment)
}