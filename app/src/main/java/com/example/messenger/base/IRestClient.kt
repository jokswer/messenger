package com.example.messenger.base

/**
 * Created by anatoliy on 23.07.18.
 */

interface IRestClient {

    fun <S> createService(serviceClass: Class<S>): S

    fun cancelAllRequests()
}