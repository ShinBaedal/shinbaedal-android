package com.example.hackathon.base

import com.example.hackathon.data.pref.Pref
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request =
            chain.request().newBuilder().addHeader("Authorization", "Bearer " + Pref.token ?: "")
                .build()
        return chain.proceed(request)
    }
}