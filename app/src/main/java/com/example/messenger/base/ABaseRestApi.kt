package com.example.messenger.base

import java.lang.reflect.ParameterizedType

/**
 * Created by anatoliy on 23.07.18.
 *
 * Базовый класс RestApi который позволит не писать явный код на создание сервиса выполнения запросов
 */
abstract class ABaseRestApi<S>: IRestApi {

    private val client: IRestClient
    val service: S

    constructor(client: IRestClient) {
        val type = javaClass.genericSuperclass as ParameterizedType
        val clazz = type.actualTypeArguments[0] as Class<S>

        this.client = client
        service = client.createService(clazz)
    }

    override fun cancelAllRequests() {
        client.cancelAllRequests()
    }
}