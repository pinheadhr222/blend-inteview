package com.interview.repo.api

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor(
    private val clientId: String
): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest =  chain.request()
            .newBuilder()
            .addHeader(authorizationKey , "$clientIdPrefix $clientId")
            .build()

        return chain.proceed(newRequest)
    }

    companion object {
        const val authorizationKey = "Authorization"
        const val clientIdPrefix = "Client-ID"
    }
}