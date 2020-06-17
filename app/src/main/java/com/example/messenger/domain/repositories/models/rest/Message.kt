package com.example.messenger.domain.repositories.models.rest

data class Message(
    val date: String? = null,
    val delivered: Boolean? = null,
    val from: Int? = null,
    val id: Int? = null,
    val message: String,
    val to: Int? = null
)