package com.example.messenger.domain.repositories.models.rest

data class Message(
    val date: String,
    val delivered: Boolean,
    val from: Int,
    val id: Int,
    val message: String,
    val to: Int
)