package com.example.omidPayTechTask.data.remote.api

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeaderInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val requestBuilder = chain.request().newBuilder()

        requestBuilder.apply {
            addHeader("Accept", "application/json")
            addHeader("Content-Type", "application/json;charset=utf-8")
        }

        val request = requestBuilder.build()

        return chain.proceed(request)
    }
}