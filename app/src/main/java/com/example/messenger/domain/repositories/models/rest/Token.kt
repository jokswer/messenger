package com.example.messenger.domain.repositories.models.rest

data class Token(
    val access: String,
    val refresh: String
)